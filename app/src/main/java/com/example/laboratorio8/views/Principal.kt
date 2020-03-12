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
import kotlinx.android.synthetic.main.principal_fragment.*


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
            viewModel.valort=binding.editText.text.toString()
            viewModel.getGithubProperties()

        }

        viewModel.status.observe(this, Observer { status ->
            status?.let {
                viewModel.status.value = null
                if(status==false){
                    binding.imageView.visibility=View.VISIBLE
                    binding.button2.visibility=View.VISIBLE
                }else if(status==true){
                    binding.imageView.visibility=View.GONE
                    binding.button2.visibility=View.GONE
                    Toast.makeText(activity,"No se pudo encontrar un usuario con ese nombre",Toast.LENGTH_SHORT).show()
                }


            }
        })

        binding.button2.setOnClickListener {
            var comment=editText.text.toString()
            binding.editText.text.clear()
            binding.imageView.visibility=View.GONE

            var bundle1= bundleOf("comentario" to comment)
        view!!.findNavController().navigate(R.id.action_principal_to_recyclerFragment,bundle1)
        }

        return binding.root

    }
}
