package com.app.xandone.yfun

import android.app.Application
import android.content.Context

import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

/**
 * author: xandone
 * created on: 2017/9/29 10:19
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        sContext = this
    }

    companion object {
        lateinit var sContext: Context

        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreater(DefaultRefreshHeaderCreater { context, layout ->
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)
                ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate)
            })
            SmartRefreshLayout.setDefaultRefreshFooterCreater(DefaultRefreshFooterCreater {
                context, layout ->
                ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate)
            })
        }
    }
}
