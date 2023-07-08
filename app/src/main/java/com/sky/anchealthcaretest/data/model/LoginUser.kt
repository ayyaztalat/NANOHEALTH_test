package com.sky.anchealthcaretest.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginUser(
    @SerializedName("token")
    @Expose
    val token: String

)
