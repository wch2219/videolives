package com.example.videolive.ui.adapters


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainFragmentAdapter(fm: FragmentManager, fragments:List<Fragment>,titles:MutableList<String>)
    : FragmentPagerAdapter(fm) {
   private var fragments:List<Fragment>?=null
    var titles:MutableList<String> = mutableListOf()
    init {
        this.fragments = fragments
        this.titles = titles
    }
    override fun getItem(position: Int): Fragment {

        return fragments?.get(position)!!
    }

    override fun getCount(): Int {
       return fragments?.size!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}