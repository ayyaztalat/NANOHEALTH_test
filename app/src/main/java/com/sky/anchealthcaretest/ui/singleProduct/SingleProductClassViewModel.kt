package com.sky.anchealthcaretest.ui.singleProduct

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sky.anchealthcaretest.model.GetAllProducts
import com.sky.anchealthcaretest.model.GetSingleProduct
import com.sky.anchealthcaretest.network.RetroFitCLient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingleProductClassViewModel : ViewModel() {


    fun callSingleProduct(id: Int):MutableLiveData<GetSingleProduct>{
        val singleProduct   = MutableLiveData<GetSingleProduct>().apply {
            callAPI(id){
                this.value = it
            }
        }
        return singleProduct
    }



    fun callAPI(id:Int,callback: (GetSingleProduct) -> Unit){
        RetroFitCLient.with().FetchSingleRecord(id).enqueue(object : Callback<GetSingleProduct> {
            override fun onResponse(
                call: Call<GetSingleProduct>,
                response: Response<GetSingleProduct>
            ) {
                if (response.code() == 200){
                    val model = response.body() as GetSingleProduct
                    callback(model)
                }else{
                    val model = GetSingleProduct()
                    callback(model)
                }
            }

            override fun onFailure(call: Call<GetSingleProduct>, t: Throwable) {
                t.printStackTrace()
                val model = GetSingleProduct()
                callback(model)
            }

        })
    }

//    fun setId(id:Int) {
//        this.id =id
//    }
}