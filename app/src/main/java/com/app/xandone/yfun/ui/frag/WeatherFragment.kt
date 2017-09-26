package com.app.xandone.yfun.ui.frag

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.app.xandone.yfun.R
import com.app.xandone.yfun.api.RetrofitClient
import com.app.xandone.yfun.bean.WeatherXml
import com.app.xandone.yfun.bean.WeatherXmlData
import com.app.xandone.yfun.config.XConfig
import com.app.xandone.yfun.ui.adapter.WeatherAdapter
import com.app.xandone.yfun.ui.base.BaseFragment
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * author: xandone
 * created on: 2017/8/23 16:22
 */
class WeatherFragment : BaseFragment() {
    lateinit var mRecycle: RecyclerView

    lateinit var mWeatherAdapter: WeatherAdapter
    var dataList = ArrayList<WeatherXml>()

    override fun getLayout(layoutId: Int): Int {
        return R.layout.frag_weather_layout
    }

    override fun initView(view: View?) {
        mRecycle = view?.findViewById(R.id.frag_weather_recycle) as RecyclerView

        mWeatherAdapter = WeatherAdapter(dataList)
        mRecycle.adapter = mWeatherAdapter
        mRecycle.layoutManager = LinearLayoutManager(this.activity!!)
    }

    override fun initData() {
        for (i in 0..3) {
            requestData(i)
        }
    }

    fun requestData(day: Int) {
        RetrofitClient.gClient
                .getWeather("%CE%E4%BA%BA", XConfig.WEATHER_PSW, day.toString())
                .compose(rxSchedulerHelper())
                .subscribe(
                        { s ->
                            dataList.add(s)
                            Log.d("xandone", s.list[0].status1)
                            mWeatherAdapter.notifyDataSetChanged()
                        },
                        { e ->
                            Log.d("xandone", "error")
                            e.printStackTrace()
                        }
                )
    }

    fun <T> rxSchedulerHelper(): FlowableTransformer<T, T> {    //compose简化线程
        return FlowableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

}