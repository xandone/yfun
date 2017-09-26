package com.app.xandone.yfun.ui.frag

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.app.xandone.yfun.R
import com.app.xandone.yfun.api.RetrofitClient
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
    var dataList = ArrayList<WeatherXmlData>()

    override fun getLayout(layoutId: Int): Int {
        return R.layout.frag_weather_layout
    }

    override fun initView(view: View?) {
        mRecycle = view?.findViewById(R.id.frag_weather_recycle) as RecyclerView

        mWeatherAdapter = WeatherAdapter(dataList)
        mRecycle.adapter = mWeatherAdapter
        mRecycle.layoutManager = LinearLayoutManager(this.activity)
    }

    override fun initData() {
        requestData()
    }

    fun requestData() {
        var map = HashMap<String, String>()
        map.put("city", "%CE%E4%BA%BA")
        map.put("password", XConfig.WEATHER_PSW)
        map.put("day", "0")
        RetrofitClient.gClient
                .getWeather(map)
                .compose(rxSchedulerHelper())
                .subscribe(
                        { s ->
                            dataList.addAll(s.list)
                            mWeatherAdapter.notifyDataSetChanged()
                        },
                        { e ->
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