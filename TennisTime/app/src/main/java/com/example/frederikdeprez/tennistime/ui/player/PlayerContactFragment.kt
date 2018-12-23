package com.example.frederikdeprez.tennistime.ui.player

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
 * [PlayerContactFragment.OnPlayerContactFragmentListener] interface
 * to handle interaction events.
 * Use the [PlayerContactFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PlayerContactFragment : Fragment() {
    private var listener: OnPlayerContactFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_contact, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.OnPlayerContactFragmentListener(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPlayerContactFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnPlayerContactFragmentListener")
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
    interface OnPlayerContactFragmentListener {
        // TODO: Update argument type and name
        fun OnPlayerContactFragmentListener(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment PlayerContactFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                PlayerContactFragment().apply {
                    }
    }
}

