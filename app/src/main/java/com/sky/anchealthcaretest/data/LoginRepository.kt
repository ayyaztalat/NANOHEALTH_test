package com.sky.anchealthcaretest.data

import com.sky.anchealthcaretest.data.model.LoggedInUser


class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String, callback:(Result<LoggedInUser>) -> Unit) {
        dataSource.login(username, password){
            if (it is Result.Success) {
                setLoggedInUser(it.data)
                callback(Result.Success(user!!))
            }else{
                callback(Result.Error((it as Result.Error).exception))
            }
        }

    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}