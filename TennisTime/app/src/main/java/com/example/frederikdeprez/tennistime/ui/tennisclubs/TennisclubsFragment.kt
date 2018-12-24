package com.example.frederikdeprez.tennistime.ui.tennisclubs

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.frederikdeprez.tennistime.R
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
    private var tennisclubs: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tennisclubs, container, false)
    }

    override fun onStart() {
        super.onStart()
        tennisclubs = createTennisclubs()
        tennisclubs_recyclerview.adapter = TennisclubsRecyclerViewAdapter(tennisclubs!!)
        tennisclubs_recyclerview.layoutManager = LinearLayoutManager(context)
    }

    private fun createTennisclubs(): List<String> {
        val tennisclubsList = mutableListOf<String>()
        tennisclubsList.add("Tennis2000")
        tennisclubsList.add("T.C. Beukenhof")
        tennisclubsList.add("T.C. De Snas")
        tennisclubsList.add("De Montil")
        tennisclubsList.add("Roland Garros")
        tennisclubsList.add("Wimbledon")
        tennisclubsList.add("Australian Open")
        tennisclubsList.add("US Open")
        return tennisclubsList
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

    class TennisclubsRecyclerViewAdapter(private val tennisclubs: List<String>): RecyclerView.Adapter<TennisclubsRecyclerViewAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TennisclubsRecyclerViewAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_tennisclub_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return tennisclubs.size
        }

        override fun onBindViewHolder(holder: TennisclubsRecyclerViewAdapter.ViewHolder, position: Int) {
            holder.tennisclub_text_view.text = tennisclubs[position]
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tennisclub_text_view = view.tennisclub_text_view
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
