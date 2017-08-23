package com.app.xandone.yfun.ui.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * author: xandone
 * created on: 2017/8/22 11:05
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doBefore()
        setContentView(setLayout(0))
        initView()
        initData()
    }

    protected abstract fun initView()

    protected abstract fun initData()

    protected abstract fun setLayout(layoutId: Int): Int

    protected fun doBefore() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}