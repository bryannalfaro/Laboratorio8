package com.example.laboratorio8.viewModels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laboratorio8.redes.ApiServices
import com.example.laboratorio8.redes.GitProperty
import retrofit2.Call
import retrofit2.Response

class PrincipalViewModel : ViewModel() {


    // TODO: Implement the ViewModel

    private val _response = MutableLiveData<String>()

    var respod:String=""
    var valort:String=""
    var status = MutableLiveData<Boolean?>()

    private val _property = MutableLiveData<GitProperty>()

    val property: LiveData<GitProperty>
        get() = _property
//In your network successfull response


    // The external immutable LiveData for the response String
    val responsa: LiveData<String>
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
    public fun getGithubProperties() {
        ApiServices.retrofitService.getProperties(valort).enqueue( object: retrofit2.Callback<GitProperty> {
            override fun onFailure(call: Call<GitProperty>, t: Throwable) {
                _response.value = "Error " + t.message
            }

            override fun onResponse(call: Call<GitProperty>, response: Response<GitProperty>){

                _response.value = "Nombre usuario: "+response.body()?.login
                if (response.body()?.login!=null){
                    _response.value = "Nombre usuario: "+response.body()?.login
                    _property.value=response.body()
                    status.value=false


                }else{
                    _response.value = "No existe"
                    status.value = true
                }
            }
        })
    }
}
