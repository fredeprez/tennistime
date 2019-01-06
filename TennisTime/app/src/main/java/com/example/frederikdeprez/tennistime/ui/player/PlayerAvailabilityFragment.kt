package com.example.frederikdeprez.tennistime.ui.player

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.frederikdeprez.tennistime.R
import com.example.frederikdeprez.tennistime.data.Tennisclub
import com.example.frederikdeprez.tennistime.databinding.FragmentPlayerAvailabilityBinding
import com.example.frederikdeprez.tennistime.databinding.FragmentPlayerDateItemBinding
import com.example.frederikdeprez.tennistime.ui.viewmodels.TennisclubsViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_player_availability.*
import kotlinx.android.synthetic.main.fragment_player_date_item.view.*
import java.time.LocalDateTime
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PlayerAvailabilityFragment.OnPlayerAvailabilityFragmentListener] interface
 * to handle interaction events.
 * Use the [PlayerAvailabilityFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PlayerAvailabilityFragment : Fragment() {
    private var listener: OnPlayerAvailabilityFragmentListener? = null
    private lateinit var tennisclubsViewModel: TennisclubsViewModel
    private lateinit var binding: FragmentPlayerAvailabilityBinding
    private lateinit var adapter: TennisclubsIdRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player_availability, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            tennisclubsViewModel = ViewModelProviders
                    .of(it).get(TennisclubsViewModel::class.java)
        }
        adapter = TennisclubsIdRecyclerViewAdapter()
        adapter.setHasStableIds(true)
        binding.datesRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@PlayerAvailabilityFragment.context)
            adapter = this@PlayerAvailabilityFragment.adapter
        }
        setupCallbacks()
    }

    private fun setupCallbacks() {
        tennisclubsViewModel.tennisclubList.observe(this,
                Observer { list -> adapter.onDataSetChange(list) })
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPlayerAvailabilityFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnPlayerAvailabilityFragmentListener")
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
    interface OnPlayerAvailabilityFragmentListener {
        // TODO: Update argument type and name
        fun OnPlayerAvailabilityFragmentListener(uri: Uri)
    }

    class TennisclubsIdRecyclerViewAdapter: RecyclerView.Adapter<TennisclubsIdRecyclerViewAdapter.ViewHolder>() {
        private var tennisclubs: List<Tennisclub> = listOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TennisclubsIdRecyclerViewAdapter.ViewHolder {
            val binding: FragmentPlayerDateItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_player_date_item, parent, false)
            return ViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return tennisclubs.size
        }

        override fun onBindViewHolder(holder: TennisclubsIdRecyclerViewAdapter.ViewHolder, position: Int) {
            holder.bind(tennisclubs[position])
        }

        fun onDataSetChange(tennisclubList: List<Tennisclub>) {
            tennisclubs = tennisclubList
            notifyDataSetChanged()
        }

        inner class ViewHolder(private val binding: FragmentPlayerDateItemBinding) : RecyclerView.ViewHolder(binding.root) {

            fun bind(tennisclub: Tennisclub) {
                binding.tennisclub = tennisclub
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment PlayerAvailabilityFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                PlayerAvailabilityFragment().apply {
                }
    }
}
