package com.app.xandone.yfun.ui.frag

import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.xandone.yfun.R
import com.app.xandone.yfun.bean.FunBean
import com.app.xandone.yfun.ui.adapter.FunAdapter
import com.app.xandone.yfun.ui.base.BaseFragment

/**
 * author: xandone
 * created on: 2017/8/23 16:24
 */
class FunnyFragment : BaseFragment() {
    lateinit var funAdapter: FunAdapter
    var dataList = ArrayList<FunBean>()
    lateinit var mRecycle: RecyclerView

    override fun getLayout(layoutId: Int): Int {
        return R.layout.frag_funny_layout
    }

    override fun initView(view: View?) {
        mRecycle = view?.findViewById(R.id.frag_fun_recycle) as RecyclerView
        funAdapter = FunAdapter(dataList)
        mRecycle.adapter = funAdapter
        mRecycle.layoutManager = LinearLayoutManager(this.activity)
    }

    override fun initData() {
        for (i in 0..16) {
            dataList.add(FunBean("山上有个庙", "庙里有个老和尚和小和尚", ""))
        }
        funAdapter.notifyDataSetChanged()
    }

}