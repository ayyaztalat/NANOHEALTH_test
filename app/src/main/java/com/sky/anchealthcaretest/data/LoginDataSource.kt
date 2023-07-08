package com.sky.anchealthcaretest.data

import com.sky.anchealthcaretest.data.model.LoggedInUser
import com.sky.anchealthcaretest.data.model.LoginUser
import com.sky.anchealthcaretest.network.APIService
import com.sky.anchealthcaretest.network.RetroFitCLient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class LoginDataSource {

    fun login(userName: String, password: String ,callback: (Result<LoggedInUser>) -> Unit){
         try {
           RetroFitCLient.with().LoginUser(LoggedInUser(userName,password)).enqueue(object :Callback<LoginUser>{
               override fun onResponse(call: Call<LoginUser>, response: Response<LoginUser>) {
                   if (response.code() == 200) {
                       callback(Result.Success(LoggedInUser(userName,password)))
                   }
               }

               override fun onFailure(call: Call<LoginUser>, t: Throwable) {
                 callback(Result.Error(IOException("Error logging in", t)))
               }

           })
        } catch (e: Throwable) {
             callback(Result.Error(IOException("Error logging in", e)))
        }
    }

    fun logout() {

    }
}