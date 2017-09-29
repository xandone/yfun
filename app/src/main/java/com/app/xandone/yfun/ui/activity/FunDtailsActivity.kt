package com.app.xandone.yfun.ui.activity

import android.animation.ObjectAnimator
import android.support.v7.widget.Toolbar
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.ScrollView
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
    lateinit var mScrollView: ScrollView

    var mScaling = false
    var img_w: Int = 0
    var img_h: Int = 0
    var mFirstPosition = 0f

    override fun initView() {
        funBean = intent.getSerializableExtra(FunAdapter.FUNADAPTER_POTISION) as FunBean
        mTitle = findViewById(R.id.fun_details_title) as TextView
        mContent = findViewById(R.id.fun_details_content) as TextView
        mImg = findViewById(R.id.fun_details_title_img) as ImageView
        mToolbar = findViewById(R.id.toolBar) as Toolbar
        mScrollView = findViewById(R.id.fun_details_title_sv) as ScrollView
    }

    override fun initData() {
        mTitle.text = funBean.title
        mContent.text = funBean.content
        Glide.with(this.applicationContext)
                .load(funBean.imgUrl)
                .into(mImg)
        mToolbar.setTitle(R.string.app_fun_name_details)

        mImg.post {
            img_w = mImg.width
            img_h = mImg.height
        }
        initEvent()
    }

    override fun setLayout(layoutId: Int): Int = R.layout.act_fun_details_layout


    fun initEvent() {
        mScrollView.setOnTouchListener OnTouchListener@ { v, event ->
            var lp = mImg.layoutParams
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    mScaling = false
                    animImg()
                }
                MotionEvent.ACTION_MOVE -> {
                    if (!mScaling) {
                        if (mScrollView.scrollY == 0) {
                            mFirstPosition = event.y
                        } else {
                            return@OnTouchListener false
                        }
                    }
                    val distance = ((event.y - mFirstPosition) * SCALE_VALUE).toInt()
                    if (distance >= 0) {
                        mScaling = true
                        lp.width = img_w + distance
                        lp.height = img_h + distance
                        mImg.layoutParams = lp
                        return@OnTouchListener true
                    }

                }

            }
            false
        }
    }

    fun animImg() {
        val lp = mImg.layoutParams
        val w = lp.width.toFloat()
        val h = lp.height.toFloat()
        val valueAnimator = ObjectAnimator.ofFloat(0.0f, 1.0f).setDuration(200)
        valueAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            lp.width = (w - (w - img_w) * value).toInt()
            lp.height = (h - (h - img_h) * value).toInt()
            mImg.layoutParams = lp
        }
        valueAnimator.start()
    }

    companion object {
        val SCALE_VALUE = 0.6f
    }
}