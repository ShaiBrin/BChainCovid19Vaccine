package com.log450.bchainvoteversion1.Actors.Voter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.log450.bchainvoteversion1.FragmentStats
import com.log450.bchainvoteversion1.FragmentVoting
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.Utils.Constants
import com.log450.bchainvoteversion1.Utils.Constants.EXTRA_ID


class Menu : AppCompatActivity() {
    lateinit var voter: Voter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val id = intent.getStringExtra(EXTRA_ID) as String

        loadBottomNavigation(id)
    }

    fun loadBottomNavigation(id:String) {
        this.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.StatsOptions -> {
                        loadFragment(FragmentStats())
                        return@setOnNavigationItemSelectedListener true
                    }

                    R.id.voteOption -> {
                        loadFragment(FragmentVoting(id))
                        return@setOnNavigationItemSelectedListener true
                    }
                }
                false
            }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameContainerIdentifiant, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}