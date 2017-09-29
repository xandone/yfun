package com.app.xandone.yfun.ui.activity

import android.os.Build
import android.support.v7.widget.Toolbar
import android.text.Html
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import com.app.xandone.yfun.R
import com.app.xandone.yfun.api.ApiConstants
import com.app.xandone.yfun.api.ApiService
import com.app.xandone.yfun.api.RetrofitClient
import com.app.xandone.yfun.bean.BB
import com.app.xandone.yfun.ui.adapter.FunAdapter
import com.app.xandone.yfun.ui.base.BaseActivity
import com.bumptech.glide.Glide
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * author: xandone
 * created on: 2017/8/27 22:19
 */
class NbaDtailsActivity : BaseActivity() {
    lateinit var nbaBean: BB.T1348649145984Bean

    lateinit var mContent: TextView
    lateinit var mImg: ImageView
    lateinit var mToolbar: Toolbar

    override fun initView() {
        nbaBean = intent.getSerializableExtra(FunAdapter.FUNADAPTER_POTISION) as BB.T1348649145984Bean
        mContent = findViewById(R.id.joke_details_content) as TextView
        mImg = findViewById(R.id.joke_details_title_img) as ImageView
        mToolbar = findViewById(R.id.toolbar) as Toolbar
    }

    override fun initData() {
        getNbaDetail(nbaBean.postid)
        Glide.with(this.applicationContext)
                .load(nbaBean.imgsrc)
                .into(mImg)
        mToolbar.title = nbaBean.title
    }

    override fun setLayout(layoutId: Int): Int = R.layout.act_nba_details_layout

    fun getNbaDetail(postId: String) {
        RetrofitClient.createRetrofitJson(ApiService::class.java, ApiConstants.NBA_URL)
                .getNbaDetail(postId)
                .compose(rxSchedulerHelper())
                .map { map ->
                    val newsDetail = map[postId]
                    newsDetail
                }
                .subscribe(
                        { s ->
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                mContent.text = Html.fromHtml(s!!.body, Html.FROM_HTML_MODE_COMPACT)
                            } else {
                                mContent.text = Html.fromHtml(s!!.body)
                            }
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