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

    private val _responseReceived = MutableLiveData<String>()
    var valort:String=""
    var status = MutableLiveData<Boolean?>()

    private val _propertyGit = MutableLiveData<GitPropertyClass>()

    val property: LiveData<GitPropertyClass>
        get() = _propertyGit

    //Response of the GitProperty
    val responsa: LiveData<String>
        get() = _responseReceived

    fun getGithubPropertiesFromJson() {
        ApiServices.retrofitService.getProperties(valort).enqueue( object: retrofit2.Callback<GitPropertyClass> {
            override fun onFailure(call: Call<GitPropertyClass>, t: Throwable) {
                _responseReceived.value = "Error " + t.message
            }

            override fun onResponse(call: Call<GitPropertyClass>, response: Response<GitPropertyClass>){

                _responseReceived.value = "Nombre usuario: "+response.body()?.login
                if (response.body()?.login!=null){
                    _responseReceived.value = "Nombre usuario: "+response.body()?.login
                    _propertyGit.value=response.body()
                    status.value=false


                }else{
                    _responseReceived.value = "No existe"
                    status.value = true
                }
            }
        })
    }
}
