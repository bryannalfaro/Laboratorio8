package com.example.laboratorio8.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laboratorio8.redes.ApiServices
import com.example.laboratorio8.redes.GitProperty
import com.example.laboratorio8.redes.ReposProperty
import retrofit2.Call
import retrofit2.Response

class RecyclerViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var _response = MutableLiveData<List<ReposProperty>>()


    var valor:String="bryannalfaro"


//In your network successfull response


    // The external immutable LiveData for the response String
    val responsa: LiveData<List<ReposProperty>>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {

    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    public fun getReposProperties() {

        ApiServices.retrofitService.getPropertiesRepo(valor).enqueue(object : retrofit2.Callback<List<ReposProperty>>{
            override fun onFailure(call: Call<List<ReposProperty>>, t: Throwable) {
                Log.i("Fallo","${t.message}")//To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<List<ReposProperty>>,
                response: Response<List<ReposProperty>>
            ) {
                Log.i("ENTRO","${response.body()!!.get(0).name}")
                _response.value=response.body()!!
            }

        })
    }
}
