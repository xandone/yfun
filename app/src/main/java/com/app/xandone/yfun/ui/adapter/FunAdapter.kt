package com.app.xandone.yfun.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.xandone.yfun.R
import com.app.xandone.yfun.bean.FunBean
import kotlinx.android.synthetic.main.item_fun_recycle.view.*//直接可以调用layout view id

/**
 * author: xandone
 * created on: 2017/8/24 13:20
 */
class FunAdapter(var dataList: List<FunBean>) : RecyclerView.Adapter<FunAdapter.FunViewHolder>() {
    override fun onBindViewHolder(holder: FunViewHolder, position: Int) {
        bindView(holder.itemView, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_fun_recycle, parent, false)
        return FunViewHolder(itemView)
    }

    override fun getItemCount(): Int = dataList.size

    fun bindView(itemView: View, position: Int) {
        var funBean = dataList[position]
        itemView.fun_item_title.text = funBean.title
        itemView.fun_item_content.text = funBean.title
    }

    class FunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}




