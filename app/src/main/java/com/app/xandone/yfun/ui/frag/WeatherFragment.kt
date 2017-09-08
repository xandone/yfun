package com.app.xandone.yfun.ui.frag

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.app.xandone.yfun.R
import com.app.xandone.yfun.api.RetrofitClient
import com.app.xandone.yfun.bean.WeatherBean
import com.app.xandone.yfun.ui.adapter.WeatherAdapter
import com.app.xandone.yfun.ui.base.BaseFragment
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.URLEncoder
import java.util.*

/**
 * author: xandone
 * created on: 2017/8/23 16:22
 */
class WeatherFragment : BaseFragment() {
    lateinit var mRecycle: RecyclerView

    lateinit var mWeatherAdapter: WeatherAdapter
    var dataList = ArrayList<WeatherBean>()

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
        for (i in 0..3) {
            dataList.add(WeatherBean("天气" + i, "晴天"))
        }
        mWeatherAdapter.notifyDataSetChanged()

        requestData()
    }

    fun requestData() {
        var current_city_code = URLEncoder.encode("武汉", "gb2312").substring(0, 12)
        var map = HashMap<String, String>()
        map.put("city", "%CE%E4%BA%BA")
        map.put("password", "DJOYnieT8234jlsK")
        map.put("day", "0")
        RetrofitClient.gClient
                .getWeather(map)
                .compose(rxSchedulerHelper())
                .subscribe(
                        { s ->
                            Log.d("xandone", "成功:" + s.list[0].status1)
                        },
                        { e ->
                            Log.d("xandone", "错误:" + e.printStackTrace())
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