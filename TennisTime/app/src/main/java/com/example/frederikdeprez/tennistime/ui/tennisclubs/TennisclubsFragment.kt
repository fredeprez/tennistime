package com.example.frederikdeprez.tennistime.ui.tennisclubs

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.frederikdeprez.tennistime.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tennisclubs, container, false)
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