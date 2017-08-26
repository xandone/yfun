package com.app.xandone.yfun.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.app.xandone.yfun.App
import com.app.xandone.yfun.R
import com.app.xandone.yfun.bean.FunBean
import kotlinx.android.synthetic.main.item_fun_recycle.view.*//直接可以调用layout view id

/**
 * author: xandone
 * created on: 2017/8/24 13:20
 */
class FunAdapter(var dataList: List<FunBean>, var context: Context) : RecyclerView.Adapter<FunAdapter.FunViewHolder>() {
    var mLastPosition = -1

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
        itemView.fun_item_title.text = funBean.title
        itemView.fun_item_content.text = funBean.content
    }

    fun showAnim(view: View, position: Int) {
        if (position > mLastPosition) {
            view.post {
                var anim = AnimationUtils.loadAnimation(context, R.anim.item_left_slide)
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




