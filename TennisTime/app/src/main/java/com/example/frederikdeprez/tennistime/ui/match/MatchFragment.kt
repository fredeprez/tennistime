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
import android.content.Intent
import android.view.ViewAnimationUtils
import android.opengl.ETC1.getHeight
import android.widget.FrameLayout
import android.opengl.ETC1.getWidth
import android.os.Build
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.frederikdeprez.tennistime.data.Player
import com.example.frederikdeprez.tennistime.databinding.FragmentMatchBinding
import com.example.frederikdeprez.tennistime.databinding.FragmentMatchItemBinding
import com.example.frederikdeprez.tennistime.ui.viewmodels.MatchViewModel
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
    private lateinit var matchViewModel: MatchViewModel
    private lateinit var binding: FragmentMatchBinding
    private lateinit var adapter: MatchesRecyclerViewAdapter

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_match, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            matchViewModel = ViewModelProviders
                    .of(it).get(MatchViewModel::class.java)
        }
        adapter = MatchesRecyclerViewAdapter(matchViewModel)
        adapter.setHasStableIds(true)
        binding.matchesRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@MatchFragment.context)
            adapter = this@MatchFragment.adapter
        }
        adapter.setOnItemClickListener(object : MatchesRecyclerViewAdapter.OnItemClickListener {
            override fun onClick(view: View, view2: View) {
                launchContacts(view, view2)
            }

            override fun callClick(view: View, player: Player) {
                val callIntent = Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+ player.phonenumber));
                startActivity(callIntent);
            }

            override fun mailClick(view: View, player: Player) {
                val emailIntent = Intent(android.content.Intent.ACTION_SEND)
                emailIntent.type = "plain/text"
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, arrayOf(player.email))
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Afspraak om te tennissen");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Beste " + player.name + " Ik heb u gevonden via de TennisTime app en zou graag afspreken om te tennissen.");
                startActivity(emailIntent)
            }
        })
        setupCallbacks()
    }

    private fun setupCallbacks() {
        matchViewModel.playerList.observe(this,
                Observer { list -> adapter.onDataSetChange(list) })
    }

    override fun onStart() {
        super.onStart()
    }



    public fun launchContacts(view: View, view2: View) {

        /*
         MARGIN_RIGHT = 16;
         FAB_BUTTON_RADIUS = 28;
         */
        var x = match_imageView.right
        val y = match_imageView.bottom
        x -= ((28 * pixelDensity) + (16 * pixelDensity)).toInt()

        val hypotenuse = Math.hypot(match_imageView.width.toDouble(), match_imageView.height.toDouble()).toInt()

        if (flag) {

            view.launch_contact_animation.setBackgroundResource(R.drawable.rounded_button)
            view.launch_contact_animation.setImageResource(R.drawable.ic_cancel)

            val parameters = view2.match_linearView.layoutParams as FrameLayout.LayoutParams
            parameters.height = match_imageView.height
            view2.match_linearView.layoutParams = parameters

            val anim = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ViewAnimationUtils.createCircularReveal(view2.match_linearView, x, y, 0f, hypotenuse.toFloat())
            } else {
                TODO("VERSION.SDK_INT < LOLLIPOP") // Todo and make linearlayout visible without effect
            }
            anim.duration = 500

            anim.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {

                }

                override fun onAnimationEnd(animator: Animator) {
                    view2.match_layoutButtons.visibility = (View.VISIBLE)
                    view2.match_layoutButtons.startAnimation(alphaAnimation)
                }

                override fun onAnimationCancel(animator: Animator) {

                }

                override fun onAnimationRepeat(animator: Animator) {

                }
            })

            view2.match_linearView.visibility = View.VISIBLE
            anim.start()

            flag = false
        } else {

            view.launch_contact_animation.setBackgroundResource(R.drawable.rounded_button)
            view.launch_contact_animation.setImageResource(R.drawable.ic_more)

            val anim = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ViewAnimationUtils.createCircularReveal(view2.match_linearView, x, y, hypotenuse.toFloat(), 0f)
            } else {
                TODO("VERSION.SDK_INT < LOLLIPOP")
            }
            anim.duration = 400

            anim.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {

                }

                override fun onAnimationEnd(animator: Animator) {
                    view2.match_linearView.visibility = View.GONE
                    view2.match_layoutButtons.visibility = (View.GONE)
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

    class MatchesRecyclerViewAdapter(private val actions: MatchListAdapterActions): RecyclerView.Adapter<MatchesRecyclerViewAdapter.ViewHolder>() {
        private var matches: List<Player> = listOf()
        lateinit var listener: OnItemClickListener

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            setOnItemClickListener(listener)
            val binding: FragmentMatchItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_match_item, parent, false)
            return ViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return matches.size
        }

        override fun getItemId(position: Int): Long = matches[position].playerId.toLong()

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(matches[position])
        }

        fun onDataSetChange(matchList: List<Player>) {
            matches = matchList
            notifyDataSetChanged()
        }

        inner class ViewHolder(private val binding: FragmentMatchItemBinding) : RecyclerView.ViewHolder(binding.root) {

            fun bind(player:Player) {
                binding.player = player
                binding.launchContactAnimation.setOnClickListener { listener.onClick(it, binding.matchLinearView)}
                binding.matchCallbutton.setOnClickListener { listener.callClick(it, player)}
                binding.matchMailbutton.setOnClickListener { listener.mailClick(it, player) }
            }
        }

        interface OnItemClickListener {
            fun onClick(view: View, view2: View)
            fun callClick(view: View, player: Player)
            fun mailClick(view: View, player: Player)
        }

        fun setOnItemClickListener(listener: OnItemClickListener) {
            this.listener = listener
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

interface MatchListAdapterActions {
    fun pressButton(player: Player)
}

