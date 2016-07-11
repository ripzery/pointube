package com.socket9.pointube.utils

import com.socket9.pointube.screens.home.HomeModel
import com.socket9.pointube.screens.home.LoginModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.POST
import rx.Observable

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */
object RetrofitUtils {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "http://pointube.socket9.com/"

    fun getInstance(): PointubeAPI {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        return retrofit!!.create(PointubeAPI::class.java)

    }

}

interface PointubeAPI {
    @POST("api/services/app/provider/GetAll")
    fun getAllProvider(): Observable<HomeModel.AllBrands>

    @POST("/Account/Login")
    fun login(@Field("Email") email: String, @Field("Password") password: String): Observable<LoginModel.Login>
}