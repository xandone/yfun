package com.app.xandone.yfun

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import com.app.xandone.yfun.ui.adapter.MainVpAdaper
import com.app.xandone.yfun.ui.base.BaseActivity
import com.app.xandone.yfun.ui.frag.FunnyFragment
import com.app.xandone.yfun.ui.frag.WeatherFragment
import java.util.ArrayList

/**
 * author: xandone
 * created on: 2017/8/22 11:03
 */

class MainActivity : BaseActivity() {
    //lateinit修饰符只能修饰不可空类型，并且不允许修饰基础类型（四类八种基础类型int， double，boolean等）
    lateinit var mainVp: ViewPager
    lateinit var toolbar: Toolbar
    lateinit var tabLayout: TabLayout

    lateinit var weatherFrag: WeatherFragment
    lateinit var funnyFrag: FunnyFragment

    lateinit var fragments: ArrayList<Fragment>
    var titlesRes: ArrayList<Int> = arrayListOf(R.string.app_weather_name, R.string.app_fun_name)
    lateinit var mainVpAadpter: MainVpAdaper

    override fun setLayout(layoutId: Int): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mainVp = findViewById(R.id.main_vp) as ViewPager
        toolbar = findViewById(R.id.tab_toolBar) as Toolbar
        tabLayout = findViewById(R.id.tabLayout) as TabLayout

        fragments = ArrayList()
        weatherFrag = WeatherFragment()
        funnyFrag = FunnyFragment()
        fragments.add(weatherFrag)
        fragments.add(funnyFrag)
        var titles = titlesRes.map(this::getString)
        mainVpAadpter = MainVpAdaper(fragments, titles, supportFragmentManager)
        mainVp.adapter = mainVpAadpter
        tabLayout.setupWithViewPager(mainVp)

    }

    override fun initData() {
    }


}