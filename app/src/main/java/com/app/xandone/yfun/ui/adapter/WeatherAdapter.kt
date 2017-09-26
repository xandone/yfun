package com.app.xandone.yfun.ui.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.xandone.yfun.R
import com.app.xandone.yfun.bean.WeatherXmlData
import kotlinx.android.synthetic.main.item_weather_one.view.*

/**
 * author: xandone
 * created on: 2017/8/24 14:38
 */
class WeatherAdapter(var dataList: List<WeatherXmlData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val ITEM_TYPE_ONE: Int = 1
    val ITEM_TYPE_NORMAL: Int = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_TYPE_ONE) {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_one, parent, false)
            return WeatherHolderOne(view)
        } else {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_other, parent, false)
            return WeatherHolderOther(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        var weatherBean: WeatherXmlData = dataList[position]
        if (holder is WeatherHolderOne) {
            holder.bindView(holder.itemView, weatherBean)
        } else if (holder is WeatherHolderOther) {
            holder.bindView(holder.itemView, weatherBean)
        }
    }

    override fun getItemCount(): Int = dataList.size
    override fun getItemViewType(position: Int): Int = if (position == 0) ITEM_TYPE_ONE else ITEM_TYPE_NORMAL

    class WeatherHolderOne(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(view: View, weatherBean: WeatherXmlData) {
            view.one_status.text = weatherBean.status1
            view.one_status_temp_l.text = weatherBean.temperature1
            view.one_status_temp_h.text = weatherBean.temperature2
        }
    }

    class WeatherHolderOther(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(view: View, weatherBean: WeatherXmlData) {
        }
    }


}