package com.socket9.pointube.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.socket9.pointube.screens.home.HomeModel
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.screens.register.RegisterModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */
object RetrofitUtils {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "http://pointube.socket9.com/"
    private val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

    fun getInstance(): PointubeAPI {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
        }

        return retrofit!!.create(PointubeAPI::class.java)

    }

}

interface PointubeAPI {
    @POST("api/services/app/provider/GetAll")
    fun getAllProvider(): Observable<HomeModel.AllBrands>

    @FormUrlEncoded
    @POST("Account/Login")
    fun login(@Field("Email") email: String, @Field("Password") password: String): Observable<LoginModel.Login>

    @FormUrlEncoded
    @POST("api/Member/Create")
    fun register(@Field("FirstName") firstName: String,
                 @Field("LastName") lastName: String,
                 @Field("CitizenID") citizenID: String,
                 @Field("Passport") passport: String,
                 @Field("Mobile") mobile: String,
                 @Field("Email") email: String,
                 @Field("Password") password: String,
                 @Field("Gender") gender: String,
                 @Field("Address") address: String,
                 @Field("Birthday") birthday: String,
                 @Field("FirstNameEN") firstNameEN: String,
                 @Field("LastNameEN") lastNameEN: String) : Observable<RegisterModel.Register>
}