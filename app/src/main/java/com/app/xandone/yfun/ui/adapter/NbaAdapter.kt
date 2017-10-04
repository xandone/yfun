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
import android.widget.ImageView
import com.app.xandone.yfun.R
import com.app.xandone.yfun.bean.NbaBean
import com.app.xandone.yfun.ui.activity.NbaDtailsActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_nba_recycler.view.*
import kotlinx.android.synthetic.main.item_nba_vp.view.*

/**
 * author: xandone
 * created on: 2017/8/24 14:38
 */
class NbaAdapter(var dataList: List<NbaBean.T1348649145984Bean>, var activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val ITEM_TYPE_ONE: Int = 1
    val ITEM_TYPE_NORMAL: Int = 2
    var mLastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_TYPE_ONE) {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_nba_vp, parent, false)
            return NbaOne(view)
        } else {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_nba_recycler, parent, false)
            return NbaOther(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is NbaOne) {
            holder.bindView(holder.itemView, position)
        } else if (holder is NbaOther) {
            holder.bindView(holder.itemView, position)
            showAnim(holder.itemView, position)
        }
    }

    override fun getItemCount(): Int = dataList.size
    override fun getItemViewType(position: Int): Int = if (position == 0) ITEM_TYPE_ONE else ITEM_TYPE_NORMAL

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

    inner class NbaOne(view: View) : RecyclerView.ViewHolder(view) {
        val imgs = ArrayList<ImageView>()
        var vpAdapter: NbaVpAdapter

        init {
            vpAdapter = NbaVpAdapter(imgs)
            view.item_nba_vp_root.adapter = vpAdapter
        }

        fun bindView(view: View, position: Int) {
            imgs.clear()
            val len = if (dataList.size > 3) 3 else dataList.size
            for (i in 0..len) {
                val iv = ImageView(activity)
                iv.scaleType = ImageView.ScaleType.CENTER_CROP
                imgs.add(iv)
                Glide.with(activity.applicationContext)
                        .load(dataList[i].imgsrc)
                        .into(iv)
            }
            vpAdapter.notifyDataSetChanged()
        }
    }

    inner class NbaOther(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(view: View, position: Int) {
            Glide.with(activity.applicationContext)
                    .load(dataList[position].imgsrc)
                    .into(itemView.nba_item_img)
            view.nba_item_title.text = dataList[position].title
            view.nba_item_content.text = dataList[position].digest
            view.nba_item_root.setOnClickListener {
                val intent = Intent(activity, NbaDtailsActivity::class.java)
                intent.putExtra(FunAdapter.FUNADAPTER_POTISION, dataList[position])
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity,
                            Pair.create<View, String>(view.nba_item_title as View?, "joke_item_title_trans"),
                            Pair.create<View, String>(view.nba_item_content as View?, "joke_item_content_trans"),
                            Pair.create<View, String>(view.nba_item_img as View?, "joke_item_img_trans")).toBundle())
                } else {
                    activity.startActivity(intent)
                }
            }
        }
    }

}