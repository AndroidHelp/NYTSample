package com.nytsample.network

import com.nytsample.utils.AppUtils.Companion.ApiKey
import com.nytsample.view.MostPopularItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface APIMethods {

    @GET("svc/{type}/v2/{viewedType}/{sections}/{period}.json?api-key="+ApiKey)
    fun getMostPopular(@Path("type") type:String,@Path("viewedType") viewedType:String,@Path("sections") sections:String,@Path("period") period:String): Observable<Response<MostPopularItem>>
}