package com.app.xandone.yfun.bean

import android.annotation.SuppressLint
import java.io.Serializable

/**
 * author: xandone
 * created on: 2017/8/24 13:25
 */

@SuppressLint("ParcelCreator")
data class FunBean(var title: String?, var content: String?, var imgUrl: String?, var date: String?) : Serializable

data class WeatherBean(var title: String, var content: String)