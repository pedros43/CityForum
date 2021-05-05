package my.app.cityforum.api

import retrofit2.Call
import retrofit2.http.*

interface EndPoints {

    @GET ("/myslim/api/users")
    fun getUsers(): Call<List<User>>

    @GET ("/myslim/api/users/{id}")
    fun getUserById(@Path("id") id:Int): Call<User>

    @GET ("/myslim/api/problemas")
    fun getReports(): Call<List<Problemas>>

    @GET ("/myslim/api/problemas/{id}")
    fun getReportById(@Path("id") id: String?): Call<List<Problemas>>

    @FormUrlEncoded
    @POST("/myslim/api/login")
    fun login(@Field("username") username: String?, @Field("password") password: String?): Call<OutputLogin>

}