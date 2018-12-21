package com.example.frederikdeprez.tennistime.ui.tabs

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.frederikdeprez.tennistime.R
import com.example.frederikdeprez.tennistime.ui.match.MatchFragment
import com.example.frederikdeprez.tennistime.ui.player.PlayerFragment
import com.example.frederikdeprez.tennistime.ui.tennisclubs.TennisclubsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PlayerFragment.OnPlayerFragmentListener,
                                            TennisclubsFragment.OnTennisclubsFragmentListener,
                                            MatchFragment.OnMatchFragmentListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_player -> {
                    loadFragment(PlayerFragment.newInstance())
                    true
                }
                R.id.navigation_search -> {
                    loadFragment(TennisclubsFragment.newInstance())
                    true
                }
                R.id.navigation_match -> {
                    loadFragment(MatchFragment.newInstance())
                    true
                }
                else -> {
                    loadFragment(PlayerFragment.newInstance())
                    true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun OnPlayerFragmentListener(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnTennisclubsFragmentListener(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnMatchFragmentListener(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
