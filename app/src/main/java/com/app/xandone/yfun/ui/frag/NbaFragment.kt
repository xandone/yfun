package com.app.xandone.yfun.ui.frag

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.app.xandone.yfun.R
import com.app.xandone.yfun.api.ApiConstants
import com.app.xandone.yfun.api.ApiService
import com.app.xandone.yfun.api.RetrofitClient
import com.app.xandone.yfun.bean.BB
import com.app.xandone.yfun.ui.adapter.NbaAdapter
import com.app.xandone.yfun.ui.base.BaseFragment
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

/**
 * author: xandone
 * created on: 2017/9/28 22:48
 */
class NbaFragment : BaseFragment() {
    lateinit var mRecycle: RecyclerView
    lateinit var mRefresh: SmartRefreshLayout

    lateinit var mNbaAdapter: NbaAdapter
    var dataList = ArrayList<BB.T1348649145984Bean>()

    override fun getLayout(layoutId: Int): Int {
        return R.layout.frag_weather_layout
    }

    override fun initView(view: View?) {
        mRecycle = view?.findViewById(R.id.frag_weather_recycle) as RecyclerView
        mRefresh = view.findViewById(R.id.frag_fun_refresh) as SmartRefreshLayout

        mNbaAdapter = NbaAdapter(dataList, this.activity)
        mRecycle.adapter = mNbaAdapter
        mRecycle.layoutManager = LinearLayoutManager(this.activity!!)

        mRefresh.setOnRefreshListener {
            getNba()
        }
    }

    override fun initData() {
        getNba()
    }

    fun getNba() {
        RetrofitClient.createRetrofitJson(ApiService::class.java, ApiConstants.NBA_URL)
                .getNBAdata(0)
                .compose(rxSchedulerHelper())
                .subscribe(
                        { s ->
                            dataList.clear()
                            dataList.addAll(s.t1348649145984)
                            mRefresh.finishRefresh()
                            mNbaAdapter.notifyDataSetChanged()
                            Log.d("yandone", s.t1348649145984!![0].lmodify)
                        },
                        { e ->
                            mRefresh.finishRefresh()
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