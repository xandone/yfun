package com.app.xandone.yfun.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * author: xandone
 * created on: 2017/8/23 16:44
 */
class MainVpAdaper(var fragments: List<Fragment>, var titles: List<String>, var fm: FragmentManager)
    : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]


}