package com.pam.sepakbolainformasi.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.pam.sepakbolainformasi.fragment.FavoriteMatchFragment
import com.pam.sepakbolainformasi.fragment.FavoriteTeamFragment

class PagerFavoriteAdapter (fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FavoriteMatchFragment()
            }

            else -> {
                FavoriteTeamFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Match"
            else -> {
                return "Teams"
            }
        }
    }

}