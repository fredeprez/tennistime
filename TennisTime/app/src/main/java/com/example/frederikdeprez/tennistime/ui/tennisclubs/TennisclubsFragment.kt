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
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.frederikdeprez.tennistime.R
import com.example.frederikdeprez.tennistime.data.Tennisclub
import com.example.frederikdeprez.tennistime.databinding.FragmentTennisclubItemBinding
import com.example.frederikdeprez.tennistime.databinding.FragmentTennisclubsBinding
import com.example.frederikdeprez.tennistime.ui.viewmodels.TennisclubViewModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.let {
            tennisclubsViewModel = ViewModelProviders
                    .of(it).get(TennisclubsViewModel::class.java)
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tennisclubs, container, false)
        binding.tennisclubsRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.viewModel = tennisclubsViewModel
        binding.setLifecycleOwner(activity)
        return binding.root
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

    class TennisclubsRecyclerViewAdapter: RecyclerView.Adapter<TennisclubsRecyclerViewAdapter.ViewHolder>() {
        private lateinit var tennisclubs: List<Tennisclub>

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TennisclubsRecyclerViewAdapter.ViewHolder {
            val binding: FragmentTennisclubItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_tennisclub_item, parent, false)
            return ViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return if(::tennisclubs.isInitialized) tennisclubs.size else 0
        }

        override fun onBindViewHolder(holder: TennisclubsRecyclerViewAdapter.ViewHolder, position: Int) {
            holder.bind(tennisclubs[position])
        }

        fun updateTennisclubs(tennisclubs:List<Tennisclub>){
            this.tennisclubs = tennisclubs
            notifyDataSetChanged()
        }

        inner class ViewHolder(private val binding: FragmentTennisclubItemBinding) : RecyclerView.ViewHolder(binding.root) {
            private val viewModel = TennisclubViewModel()

            fun bind(tennisclub: Tennisclub){
                viewModel.bind(tennisclub)
                binding.viewModel = viewModel
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
