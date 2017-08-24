package com.app.xandone.yfun.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * author: xandone
 * created on: 2017/8/23 16:11
 */
abstract class BaseFragment : Fragment() {
    //类型后带问号?表示可为空类型(默认空安全)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(0), container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initData()
    }

    protected abstract fun getLayout(layoutId: Int): Int

    protected abstract fun initView(view: View?)
    protected abstract fun initData()


}