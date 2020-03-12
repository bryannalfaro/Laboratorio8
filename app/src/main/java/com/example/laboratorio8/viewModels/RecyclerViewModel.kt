package com.example.laboratorio8.viewModels

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
    private var _response = ArrayList<ArrayList<ReposProperty>>()


    var valor:String="bryannalfaro"


//In your network successfull response


    // The external immutable LiveData for the response String
    val responsa: ArrayList<ArrayList<ReposProperty>>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getReposProperties()
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    public fun getReposProperties() {

        ApiServices.retrofitService.getPropertiesRepo(valor).enqueue(object : retrofit2.Callback<ArrayList<ArrayList<ReposProperty>>>{
            override fun onFailure(call: Call<ArrayList<ArrayList<ReposProperty>>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<ArrayList<ArrayList<ReposProperty>>>,
                response: Response<ArrayList<ArrayList<ReposProperty>>>
            ) {
                _response=response.body()!!
            }

        })
    }
}
