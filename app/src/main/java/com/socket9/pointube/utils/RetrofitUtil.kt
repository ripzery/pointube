package com.socket9.pointube.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.socket9.pointube.repository.brands.GetMemberBrand
import com.socket9.pointube.repository.brands.GetMemberSelectBrand
import com.socket9.pointube.screens.brand.BrandModel
import com.socket9.pointube.screens.home.HomeModel
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.screens.register.RegisterModel
import com.socket9.pointube.screens.setting.SettingModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by Euro (ripzery@gmail.com) on 7/11/2016 AD.
 */
object RetrofitUtils {
    private var retrofit: Retrofit? = null
//        private val BASE_URL = "http://192.168.100.252:8099"
    private val BASE_URL = "http://service.pointube.com/"
    private val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

    fun getInstance(): PointubeAPI {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                    .addInterceptor {
                        val onGoing = it.request().newBuilder()
                        if (SharedPrefUtil.loadLoginResult() != null) {
                            val token = SharedPrefUtil.loadLoginResult()!!.token
                            onGoing.addHeader("Authorization", "Bearer $token")
                        }
                        it.proceed(onGoing.build())
                    }
                    .addInterceptor(interceptor)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .build()


            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build()
        }

        return retrofit!!.create(PointubeAPI::class.java)
    }
}

interface PointubeAPI {
    @GET("api/Provider/GetProviderList")
    fun getAllProvider(): Observable<HomeModel.AllBrands>

    @POST("api/Member/Login")
    fun login(@Body loginModel: LoginModel.Request.Login): Observable<LoginModel.Response.Login>

    @GET("api/Member/SignOut")
    fun logout(@Query("MemberId") memberId: Int) : Observable<LoginModel.Response.Login>

    @POST("api/Member/Create")
    fun register(@Body registerModel: RegisterModel.Request.Register): Observable<RegisterModel.Response.Register>

    @POST("api/Member/UpdateMember")
    fun updateProfile(@Body updateModel: SettingModel.Request.UpdateProfile): Observable<SettingModel.Response.UpdateProfile>

    @POST("api/Member/SaveMobileNo")
    fun saveMobileNo(@Body mobileModel: RegisterModel.Request.SaveMobileNo): Observable<RegisterModel.Response.SaveMobileNo>

    @POST("api/Member/VerifyPhoneNumber")
    fun verifyPhoneNumber(@Body phoneModel: RegisterModel.Request.VerifyPhoneNumber): Observable<RegisterModel.Response.VerifyPhoneNumber>

    @POST("api/Member/GetMemberBrand")
    fun getMemberBrand(@Body memberBrand: BrandModel.Request.GetMemberBrand): Observable<GetMemberBrand>

    @POST("api/Member/SaveBrand")
    fun saveBrand(@Body saveBrandModel: BrandModel.Request.SaveBrand): Observable<BrandModel.Response.SaveBrand>

    @POST("api/Member/GetMemberSelectedBrand")
    fun getMemberSelectedBrand(@Body selectedBrandModel: BrandModel.Request.GetMemberSelectBrand): Observable<GetMemberSelectBrand>

    @GET("api/Provider/GetProvider")
    fun getProviderById(@Query("ProviderId") providerId: Int)

    @GET("api/Provider/GetUnit")
    fun getUnitById(@Query("UnitId") unitId: Int)

    @GET("api/Program/GetPublishedProgramList")
    fun getPublishProgramList(): Observable<HomeModel.PublishedProgramListRepo>

    @GET("api/Program/GetProgramListByProvider")
    fun getProgramListByProvider(@Query("providerId") providerId: Int)

    @GET("api/Program/GetProgramListByUnit")
    fun getProgramListByUnit(@Query("unitId") unitId: Int)

    @GET("api/Program/GetProgram")
    fun getProgramById(@Query("programId") programId: Int)

}