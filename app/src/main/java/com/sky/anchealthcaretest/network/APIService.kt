package com.sky.anchealthcaretest.network

import com.sky.anchealthcaretest.data.model.LoggedInUser
import com.sky.anchealthcaretest.data.model.LoginUser
import com.sky.anchealthcaretest.model.GetAllProducts
import com.sky.anchealthcaretest.model.GetSingleProduct
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {

    @POST("auth/login")
    fun LoginUser(
        @Body loggedInUser: LoggedInUser
    ) : Call<LoginUser>


    @GET("products")
    fun FetchProducts(): Call<List<GetAllProducts>>

    @GET("products/{id}")
    fun FetchSingleRecord(@Path("id") id:Int): Call<GetSingleProduct>
}