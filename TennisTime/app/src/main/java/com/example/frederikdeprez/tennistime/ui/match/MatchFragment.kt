package com.example.frederikdeprez.tennistime.ui.match

import android.animation.Animator
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.frederikdeprez.tennistime.R
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_match_item.view.*
import android.animation.Animator.AnimatorListener
import android.view.ViewAnimationUtils
import android.opengl.ETC1.getHeight
import android.widget.FrameLayout
import android.opengl.ETC1.getWidth
import android.os.Build
import kotlinx.android.synthetic.main.fragment_match_item.*


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

    private lateinit var matchImageView: ImageView
    private lateinit var contactButton: ImageButton
    private lateinit var revealView: LinearLayout
    private lateinit var contactButtons: LinearLayout
    private lateinit var alphaAnimation: Animation
    private var pixelDensity: Float = 0F
    private var flag: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pixelDensity = getResources().getDisplayMetrics().density;
        alphaAnimation = AnimationUtils.loadAnimation(context, R.anim.alpha_anim)
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

    public fun launchContacts(view: View) {

        /*
         MARGIN_RIGHT = 16;
         FAB_BUTTON_RADIUS = 28;
         */
        var x = match_imageView.right
        val y = match_imageView.bottom
        x -= ((28 * pixelDensity) + (16 * pixelDensity)).toInt()

        val hypotenuse = Math.hypot(match_imageView.width.toDouble(), match_imageView.height.toDouble()).toInt()

        if (flag) {

            launch_contact_animation.setBackgroundResource(R.drawable.rounded_button)
            launch_contact_animation.setImageResource(R.drawable.ic_cancel)

            val parameters = revealView.layoutParams as FrameLayout.LayoutParams
            parameters.height = launch_contact_animation.height
            revealView.layoutParams = parameters

            val anim = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ViewAnimationUtils.createCircularReveal(revealView, x, y, 0f, hypotenuse.toFloat())
            } else {
                TODO("VERSION.SDK_INT < LOLLIPOP") // Todo and make linearlayout visible without effect
            }
            anim.duration = 500

            anim.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {

                }

                override fun onAnimationEnd(animator: Animator) {
                    match_layoutButtons.visibility = (View.VISIBLE)
                    match_layoutButtons.startAnimation(alphaAnimation)
                }

                override fun onAnimationCancel(animator: Animator) {

                }

                override fun onAnimationRepeat(animator: Animator) {

                }
            })

            revealView.visibility = View.VISIBLE
            anim.start()

            flag = false
        } else {

            launch_contact_animation.setBackgroundResource(R.drawable.rounded_button)
            launch_contact_animation.setImageResource(R.drawable.ic_more)

            val anim = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ViewAnimationUtils.createCircularReveal(revealView, x, y, hypotenuse.toFloat(), 0f)
            } else {
                TODO("VERSION.SDK_INT < LOLLIPOP")
            }
            anim.duration = 400

            anim.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {

                }

                override fun onAnimationEnd(animator: Animator) {
                    revealView.visibility = View.GONE
                    match_layoutButtons.visibility = (View.GONE)
                }

                override fun onAnimationCancel(animator: Animator) {

                }

                override fun onAnimationRepeat(animator: Animator) {

                }
            })

            anim.start()
            flag = true
        }
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
            val contactsButton = view.launch_contact_animation
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
