package com.app.xandone.yfun.ui.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * author: xandone
 * created on: 2017/10/3 23:29
 */
class NbaVpAdapter(var dataList: List<View>) : PagerAdapter() {
    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        container!!.addView(dataList[position])
        return dataList[position]
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container!!.removeView(dataList[position])
    }

}