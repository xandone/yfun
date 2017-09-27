package com.app.xandone.yfun

import android.app.Application
import android.content.Context
import com.wilddog.wilddogcore.WilddogApp

/**
 * author: xandone
 * created on: 2017/8/25 17:33
 */
class App : Application() {
    lateinit var sContext: Context
    override fun onCreate() {
        super.onCreate()
        sContext = this
    }
}