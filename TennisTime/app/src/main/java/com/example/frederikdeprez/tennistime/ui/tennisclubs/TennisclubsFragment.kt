package com.example.frederikdeprez.tennistime.ui.tennisclubs

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.frederikdeprez.tennistime.R
import com.example.frederikdeprez.tennistime.data.Tennisclub
import com.example.frederikdeprez.tennistime.databinding.FragmentTennisclubItemBinding
import com.example.frederikdeprez.tennistime.databinding.FragmentTennisclubsBinding
import com.example.frederikdeprez.tennistime.ui.match.MatchFragment
import com.example.frederikdeprez.tennistime.ui.viewmodels.TennisclubsViewModel
import kotlinx.android.synthetic.main.fragment_tennisclub_item.view.*
import kotlinx.android.synthetic.main.fragment_tennisclubs.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TennisclubsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TennisclubsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TennisclubsFragment : Fragment() {
    private var listener: OnTennisclubsFragmentListener? = null
    private lateinit var tennisclubsViewModel: TennisclubsViewModel
    private lateinit var binding: FragmentTennisclubsBinding
    private lateinit var adapter: TennisclubsRecyclerViewAdapter
    private val matchFragment = MatchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tennisclubs, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            tennisclubsViewModel = ViewModelProviders
                    .of(it).get(TennisclubsViewModel::class.java)
        }
        adapter = TennisclubsRecyclerViewAdapter(tennisclubsViewModel)
        adapter.setHasStableIds(true)
        binding.tennisclubsRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@TennisclubsFragment.context)
            adapter = this@TennisclubsFragment.adapter
        }
        setupCallbacks()
    }

    private fun setupCallbacks() {
        Log.i("FREDSON", "setupcallbacks1")
        tennisclubsViewModel.tennisclubList.observe(this,
                Observer { list -> adapter.onDataSetChange(list) }
        )
        Log.i("FREDSON", "setupcallbacks2")
        tennisclubsViewModel.selectedTennisclub.observe(this,
                Observer { event -> event.getContentIfNotHandled()?.let { navigateToMatches(matchFragment) } }
        )
    }

    private fun navigateToMatches(fragment: Fragment) {
        Log.i("FREDSON", "kan ik navigaten?")
        activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun onStart() {
        super.onStart()
//        tennisclubs_recyclerview.adapter = TennisclubsRecyclerViewAdapter()
//        tennisclubs_recyclerview.layoutManager = LinearLayoutManager(context)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.OnTennisclubsFragmentListener(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnTennisclubsFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnTennisclubsFragmentListener")
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
    interface OnTennisclubsFragmentListener {
        // TODO: Update argument type and name
        fun OnTennisclubsFragmentListener(uri: Uri)
    }

    class TennisclubsRecyclerViewAdapter(private val actions: TennisclubListAdapterActions): RecyclerView.Adapter<TennisclubsRecyclerViewAdapter.ViewHolder>() {
        private var tennisclubs: List<Tennisclub> = listOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding: FragmentTennisclubItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_tennisclub_item, parent, false)
            return ViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return tennisclubs.size
        }

        override fun getItemId(position: Int): Long = tennisclubs[position].tenniclubId.toLong()

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(tennisclubs[position])
        }

        fun onDataSetChange(tennisclubList: List<Tennisclub>) {
            tennisclubs = tennisclubList
            notifyDataSetChanged()
        }

//        fun updateTennisclubs(tennisclubs:List<Tennisclub>){
//            this.tennisclubs = tennisclubs
//            notifyDataSetChanged()
//        }

        inner class ViewHolder(private val binding: FragmentTennisclubItemBinding) : RecyclerView.ViewHolder(binding.root) {

            fun bind(tennisclub: Tennisclub){
                binding.tennisclub = tennisclub
                binding.tennisclubView.setOnClickListener { actions.select(tennisclub) }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TennisclubsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                TennisclubsFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}

interface TennisclubListAdapterActions {
    fun select(tennisclub: Tennisclub)
}
