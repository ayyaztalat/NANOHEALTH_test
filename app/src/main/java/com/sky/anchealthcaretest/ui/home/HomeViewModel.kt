package com.sky.anchealthcaretest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sky.anchealthcaretest.model.GetAllProducts
import com.sky.anchealthcaretest.network.RetroFitCLient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type


class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

     var getArrayList = MutableLiveData<ArrayList<GetAllProducts>>().apply {

        callAPI(){
            this.value = it
        }
    }

    fun callAPI(callback: (ArrayList<GetAllProducts>) -> Unit){
        RetroFitCLient.with().FetchProducts().enqueue(object :Callback<List<GetAllProducts>>{
            override fun onResponse(
                call: Call<List<GetAllProducts>>,
                response: Response<List<GetAllProducts>>
            ) {
                if (response.code() == 200){
                    val model = response.body() as List<GetAllProducts>

//                    val arrayList: ArrayList<GetAllProducts>

//                    val collectionType: Type =
//                        object : TypeToken<List<GetAllProducts?>?>() {}.type
//                    val lcs: List<GetAllProducts> = Gson()
//                        .fromJson(model, collectionType) as List<GetAllProducts>
//                    arrayList = lcs as ArrayList<GetAllProducts>
                    callback(model as ArrayList<GetAllProducts>)
                }else{
                    callback(ArrayList())
                }
            }

            override fun onFailure(call: Call<List<GetAllProducts>>, t: Throwable) {
                t.printStackTrace()
                callback(ArrayList())
            }

        })
    }
}