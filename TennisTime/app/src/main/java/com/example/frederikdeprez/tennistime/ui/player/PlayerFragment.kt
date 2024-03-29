package com.example.frederikdeprez.tennistime.ui.player

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders

import com.example.frederikdeprez.tennistime.R
import com.example.frederikdeprez.tennistime.databinding.FragmentPlayerBinding
import com.example.frederikdeprez.tennistime.ui.viewmodels.PlayerViewModel
import com.example.frederikdeprez.tennistime.util.Constants
import com.example.frederikdeprez.tennistime.util.Constants.Companion.PREFS_KEY
import kotlinx.android.synthetic.main.fragment_player.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PlayerFragment.OnPlayerFragmentListener] interface
 * to handle interaction events.
 * Use the [PlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PlayerFragment : Fragment() {
    private var listener: OnPlayerFragmentListener? = null
    private val tabTitles = arrayListOf<String>("Tennisclub IDs", "Contact")
    private lateinit var playerViewModel: PlayerViewModel
    private lateinit var binding: FragmentPlayerBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = context!!.getSharedPreferences(PREFS_KEY, Activity.MODE_PRIVATE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        activity?.let {
            playerViewModel = ViewModelProviders
                    .of(it).get(PlayerViewModel::class.java)
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false)
        binding.playerViewModel = playerViewModel
        binding.setLifecycleOwner(activity)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setName()
        player_viewpager.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                when(position) {
                    0 -> return PlayerAvailabilityFragment.newInstance()
                    1 -> return PlayerContactFragment.newInstance()
                    else -> {
                        return PlayerAvailabilityFragment.newInstance()
                    }
                }
            }
            override fun getCount(): Int {
                return 2
            }

            override fun getPageTitle(position: Int): CharSequence? {
                when(position) {
                    0 -> return tabTitles[position]
                    1 -> return tabTitles[position]
                }
                return tabTitles[0]
            }
        }
        tabs.setupWithViewPager(player_viewpager)
    }

    override fun onResume() {
        super.onResume()
        setName()
    }
    private fun setName() {
        if(playerViewModel.mutablePlayer.value != null) {
            playerViewModel.getPlayerName().value = playerViewModel.mutablePlayer.value!!.name
        } else {
            playerViewModel.getPlayerName().value = sharedPreferences.getString("name", "")
        }
    }

    fun onButtonPressed(uri: Uri) {
        listener?.OnPlayerFragmentListener(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPlayerFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnPlayerFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnPlayerFragmentListener {
        fun OnPlayerFragmentListener(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment PlayerFragment.
         */
        @JvmStatic
        fun newInstance() =
                PlayerFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
