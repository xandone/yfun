package com.app.xandone.yfun.api

import com.app.xandone.yfun.bean.WeatherXml
import io.reactivex.Flowable
import retrofit2.http.*

/**
 * author: xandone
 * created on: 2017/9/8 9:54
 */

interface ApiService {
//    @FormUrlEncoded
//    @POST("xml.php")
//    fun getWeather(@FieldMap map: Map<String, String>): Flowable<WeatherXml>

    @GET("xml.php")
    fun getWeather(
            @Query(value = "city", encoded = true) city: String,
            @Query("password") psw: String,
            @Query("day") day: String
    ): Flowable<WeatherXml>
}