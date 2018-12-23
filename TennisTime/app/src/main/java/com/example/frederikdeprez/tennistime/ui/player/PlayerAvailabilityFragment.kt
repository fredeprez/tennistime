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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.frederikdeprez.tennistime.R
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
    /**
     *  List containing the availabilities of the player
     */

    private var availabilities: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_availability, container, false)
    }

    override fun onStart() {
        super.onStart()
        availabilities = createAvailabilities()
        dates_recyclerview.adapter = DateItemRecyclerViewAdapter(availabilities!!)
        dates_recyclerview.layoutManager = LinearLayoutManager(context)
    }

    private fun createAvailabilities(): List<String> {
        val availabilityList = mutableListOf<String>()
        availabilityList.add("start1")
        availabilityList.add("end1")
        availabilityList.add("start2")
        availabilityList.add("end2")
        availabilityList.add("start3")
        availabilityList.add("end3")
        availabilityList.add("start4")
        availabilityList.add("end4")
        availabilityList.add("start5")
        availabilityList.add("end5")
        return availabilityList
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.OnPlayerAvailabilityFragmentListener(uri)
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

    class DateItemRecyclerViewAdapter(private val availabilities: List<String>): RecyclerView.Adapter<DateItemRecyclerViewAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateItemRecyclerViewAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_player_date_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return ((availabilities.size + 1)/2)
        }

        override fun onBindViewHolder(holder: DateItemRecyclerViewAdapter.ViewHolder, position: Int) {
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val startDate: TextInputEditText = view.start_date_edit
            val endDate: TextInputEditText = view.end_date_edit
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
