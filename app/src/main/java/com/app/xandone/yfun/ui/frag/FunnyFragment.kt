package com.app.xandone.yfun.ui.frag

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.app.xandone.yfun.R
import com.app.xandone.yfun.api.ApiConstants
import com.app.xandone.yfun.bean.FunBean
import com.app.xandone.yfun.ui.adapter.FunAdapter
import com.app.xandone.yfun.ui.base.BaseFragment
import com.wilddog.client.*
import com.wilddog.wilddogcore.WilddogApp
import com.wilddog.wilddogcore.WilddogOptions

/**
 * author: xandone
 * created on: 2017/8/23 16:24
 */
class FunnyFragment : BaseFragment() {
    lateinit var mRecycle: RecyclerView

    lateinit var funAdapter: FunAdapter
    var dataList = ArrayList<FunBean>()

    lateinit var mWilddogOptions: WilddogOptions
    lateinit var mRef: SyncReference

    override fun getLayout(layoutId: Int): Int {
        return R.layout.frag_funny_layout
    }

    override fun initView(view: View?) {
        mRecycle = view?.findViewById(R.id.frag_fun_recycle) as RecyclerView
        funAdapter = FunAdapter(dataList, activity)
        mRecycle.adapter = funAdapter
        mRecycle.layoutManager = LinearLayoutManager(this.activity)
    }

    override fun initData() {
        initDog()
        getData(mRef)
    }

    fun initDog() {
        mWilddogOptions = WilddogOptions.Builder().setSyncUrl(ApiConstants.FUN_URL).build()
        WilddogApp.initializeApp(this.activity.applicationContext, mWilddogOptions)
        mRef = WilddogSync.getInstance().reference
    }

    fun getData(ref: SyncReference) {
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var value = dataSnapshot.value as Map<*, *>
                var list = value["results"] as List<*>
                Log.d("xandone", list.size.toString())
                dataList.clear()
                for (obj in list) {
                    val data = obj as Map<String, String>
                    if (!TextUtils.isEmpty(data["title"])) {
                        Log.d("xandone", data["title"])
                        dataList.add(FunBean(data["title"], data["content"],
                                data["url"], data["mydate"]))
                    }
                }
                funAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(syncError: SyncError) {
            }
        })
    }

}