package com.example.laboratorio8.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laboratorio8.redes.ApiServices
import com.example.laboratorio8.redes.GitPropertyClass
import retrofit2.Call
import retrofit2.Response

/**
 * ViewModel for the Principal Fragment of the Github program
 * @author Bryann Alfaro
 */
class PrincipalViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    var valort:String=""
    var status = MutableLiveData<Boolean?>()

    private val _property = MutableLiveData<GitPropertyClass>()

    val property: LiveData<GitPropertyClass>
        get() = _property

    //Response of the GitProperty
    val responsa: LiveData<String>
        get() = _response

    fun getGithubProperties() {
        ApiServices.retrofitService.getProperties(valort).enqueue( object: retrofit2.Callback<GitPropertyClass> {
            override fun onFailure(call: Call<GitPropertyClass>, t: Throwable) {
                _response.value = "Error " + t.message
            }

            override fun onResponse(call: Call<GitPropertyClass>, response: Response<GitPropertyClass>){

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
