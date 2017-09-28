package com.app.xandone.yfun.ui.activity

import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.app.xandone.yfun.R
import com.app.xandone.yfun.bean.BB
import com.app.xandone.yfun.bean.FunBean
import com.app.xandone.yfun.ui.adapter.FunAdapter
import com.app.xandone.yfun.ui.base.BaseActivity
import com.bumptech.glide.Glide

/**
 * author: xandone
 * created on: 2017/8/27 22:19
 */
class NbaDtailsActivity : BaseActivity() {
    lateinit var nbaBean: BB.T1348649145984Bean

    lateinit var mTitle: TextView
    lateinit var mContent: TextView
    lateinit var mImg: ImageView
    lateinit var mToolbar: Toolbar

    override fun initView() {
        nbaBean = intent.getSerializableExtra(FunAdapter.FUNADAPTER_POTISION) as BB.T1348649145984Bean
        mTitle = findViewById(R.id.joke_details_title) as TextView
        mContent = findViewById(R.id.joke_details_content) as TextView
        mImg = findViewById(R.id.joke_details_title_img) as ImageView
        mToolbar = findViewById(R.id.toolBar) as Toolbar
    }

    override fun initData() {
        mTitle.text = nbaBean.title
        mContent.text = nbaBean.digest
        Glide.with(this.applicationContext)
                .load(nbaBean.imgsrc)
                .into(mImg)
        mToolbar.setTitle(R.string.app_nba_name_details)
    }

    override fun setLayout(layoutId: Int): Int = R.layout.act_fun_details_layout
}