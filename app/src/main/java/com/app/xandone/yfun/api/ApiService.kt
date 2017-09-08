package com.app.xandone.yfun.api

import com.app.xandone.yfun.bean.WeatherXml
import io.reactivex.Flowable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * author: xandone
 * created on: 2017/9/8 9:54
 */

interface ApiService {
    @FormUrlEncoded
    @POST("xml.php")
    fun getWeather(@FieldMap map: Map<String, String>): Flowable<WeatherXml>
}