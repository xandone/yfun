package com.app.xandone.yfun.ui.activity

import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.app.xandone.yfun.R
import com.app.xandone.yfun.bean.FunBean
import com.app.xandone.yfun.ui.adapter.FunAdapter
import com.app.xandone.yfun.ui.base.BaseActivity
import com.bumptech.glide.Glide

/**
 * author: xandone
 * created on: 2017/8/27 22:19
 */
class FunDtailsActivity : BaseActivity() {
    lateinit var funBean: FunBean

    lateinit var mTitle: TextView
    lateinit var mContent: TextView
    lateinit var mImg: ImageView
    lateinit var mToolbar: Toolbar

    override fun initView() {
        funBean = intent.getSerializableExtra(FunAdapter.FUNADAPTER_POTISION) as FunBean
        mTitle = findViewById(R.id.joke_details_title) as TextView
        mContent = findViewById(R.id.joke_details_content) as TextView
        mImg = findViewById(R.id.joke_details_title_img) as ImageView
        mToolbar = findViewById(R.id.toolBar) as Toolbar
    }

    override fun initData() {
        mTitle.text = funBean.title
        mContent.text = funBean.content
        Glide.with(this.applicationContext)
                .load(funBean.imgUrl)
                .into(mImg)
        mToolbar.setTitle(R.string.app_fun_name_details)
    }

    override fun setLayout(layoutId: Int): Int = R.layout.act_fun_details_layout
}