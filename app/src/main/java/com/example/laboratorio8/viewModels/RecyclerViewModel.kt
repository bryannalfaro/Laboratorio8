package com.example.laboratorio8.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laboratorio8.redes.ApiServices
import com.example.laboratorio8.redes.ReposProperty
import retrofit2.Call
import retrofit2.Response

/**
 * ViewModel for the RecyclerView
 * @author Bryann Alfaro
 */
class RecyclerViewModel : ViewModel() {

    private var _response = MutableLiveData<List<ReposProperty>>()
    var brt:Boolean=false

    var valor:String="bryannalfaro"


    val responsa: LiveData<List<ReposProperty>>
        get() = _response

    //Get the values of the retrofit service
    fun getReposProperties() {

        ApiServices.retrofitService.getPropertiesRepo(valor).enqueue(object : retrofit2.Callback<List<ReposProperty>>{
            override fun onFailure(call: Call<List<ReposProperty>>, t: Throwable) {
                Log.i("Fallo","${t.message}")
            }

            override fun onResponse(
                call: Call<List<ReposProperty>>,
                response: Response<List<ReposProperty>>
            ) {

                    _response.value=response.body()!!

            }

        })
    }
}
