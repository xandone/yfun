package com.app.xandone.yfun.api

import com.app.xandone.yfun.bean.BB
import com.app.xandone.yfun.bean.FunBean
import com.app.xandone.yfun.bean.NbaBean
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

    @GET("nc/article/list/T1348649145984/{startPage}-20.html")
    fun getNBAdata(
            @Path("startPage") startPage: Int
    ): Flowable<BB>
}