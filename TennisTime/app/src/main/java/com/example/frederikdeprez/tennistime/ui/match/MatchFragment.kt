package com.example.frederikdeprez.tennistime.ui.match

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
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_match_item.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MatchFragment.OnMatchFragmentListener] interface
 * to handle interaction events.
 * Use the [MatchFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MatchFragment : Fragment() {
    private var listener: OnMatchFragmentListener? = null
    private var matches: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onStart() {
        super.onStart()
        matches = createMatches()
        matches_recyclerview.adapter = MatchesRecyclerViewAdapter(matches!!)
        matches_recyclerview.layoutManager = LinearLayoutManager(context)
    }

    private fun createMatches() : List<String> {
        val matchesList = mutableListOf<String>()
        matchesList.add("Roger Federer") // Todo veranderen naar player eenmaal model er is
        matchesList.add("Nadal")
        matchesList.add("Djoko")
        matchesList.add("Wawrinka")
        return matchesList
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.OnMatchFragmentListener(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMatchFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnMatchFragmentListener")
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
    interface OnMatchFragmentListener {
        // TODO: Update argument type and name
        fun OnMatchFragmentListener(uri: Uri)
    }

    class MatchesRecyclerViewAdapter(private val matches: List<String>): RecyclerView.Adapter<MatchesRecyclerViewAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesRecyclerViewAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_match_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return matches.size
        }

        override fun onBindViewHolder(holder: MatchesRecyclerViewAdapter.ViewHolder, position: Int) {
            holder.matchNameTextView.text = matches[position]
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val matchNameTextView = view.match_name_textView
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment MatchFragment.
         */
        @JvmStatic
        fun newInstance() =
                MatchFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
