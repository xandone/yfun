package com.app.xandone.yfun.ui.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.app.xandone.yfun.R
import com.app.xandone.yfun.bean.FunBean
import com.app.xandone.yfun.ui.activity.FunDtailsActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_fun_recycle.view.*//直接可以调用layout view id

/**
 * author: xandone
 * created on: 2017/8/24 13:20
 */
class FunAdapter(var dataList: List<FunBean>, var activity: Activity) : RecyclerView.Adapter<FunAdapter.FunViewHolder>() {
    var mLastPosition = -1

    companion object {
        val FUNADAPTER_POTISION = "FunAdapter_potision"
    }

    override fun onBindViewHolder(holder: FunViewHolder, position: Int) {
        bindView(holder.itemView, position)
        showAnim(holder.itemView, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_fun_recycle, parent, false)
        return FunViewHolder(itemView)
    }

    override fun getItemCount(): Int = dataList.size

    fun bindView(itemView: View, position: Int) {
        var funBean = dataList[position]
        Glide.with(activity.applicationContext)
                .load(dataList[position].imgUrl)
                .into(itemView.fun_item_img)
        itemView.fun_item_title.text = funBean.title
        itemView.fun_item_content.text = funBean.content
        itemView.fun_item_root.setOnClickListener {
            val intent = Intent(activity, FunDtailsActivity::class.java)
            intent.putExtra(FUNADAPTER_POTISION, dataList[position])
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity,
                        Pair.create<View, String>(itemView.fun_item_title as View?, "joke_item_title_trans"),
                        Pair.create<View, String>(itemView.fun_item_content as View?, "joke_item_content_trans"),
                        Pair.create<View, String>(itemView.fun_item_img as View?, "joke_item_img_trans")).toBundle())
            } else {
                activity.startActivity(intent)
            }
        }

    }

    fun showAnim(view: View, position: Int) {
        if (position > mLastPosition) {
            view.post {
                var anim = AnimationUtils.loadAnimation(activity, R.anim.item_left_slide)
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        mLastPosition = position
                    }

                    override fun onAnimationStart(animation: Animation?) {
                    }

                })
                view.startAnimation(anim)

            }

        }
    }

    class FunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}




