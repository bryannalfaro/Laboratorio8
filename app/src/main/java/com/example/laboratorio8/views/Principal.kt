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

                Toast.makeText(activity,"No se pudo encontrar un usuario con ese nombre",Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root

    }
}
