package com.example.laboratorio8.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.laboratorio8.R
import com.example.laboratorio8.viewModels.PrincipalViewModel
import com.example.laboratorio8.databinding.PrincipalFragmentBinding
import com.example.laboratorio8.redes.ApiService


class Principal : Fragment() {
    private lateinit var api:ApiService

    private val viewModel:PrincipalViewModel by lazy {
        ViewModelProviders.of(this).get(PrincipalViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=PrincipalFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner (this)
        binding.viewModels=viewModel



        binding.button.setOnClickListener {
            viewModel.valor=binding.editText.text.toString()
            viewModel.getGithubProperties()

        }
        viewModel.status.observe(this, Observer { status ->
            status?.let {
                viewModel.status.value = null
                if(status==false){
                    binding.imageView.visibility=View.VISIBLE
                    binding.button2.visibility=View.VISIBLE
                }else{
                    binding.button2.visibility=View.GONE
                    binding.imageView.visibility=View.GONE
                    Toast.makeText(activity,"No se pudo encontrar un usuario con ese nombre",Toast.LENGTH_SHORT).show()
                }


            }
        })

        binding.button2.setOnClickListener {
        view!!.findNavController().navigate(R.id.action_principal_to_recyclerFragment)
        }

        return binding.root

    }
}
