package com.example.laboratorio8.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio8.Adapter
import com.example.laboratorio8.R
import com.example.laboratorio8.databinding.RecyclerFragmentBinding
import com.example.laboratorio8.redes.ReposProperty
import com.example.laboratorio8.viewModels.RecyclerViewModel


class RecyclerFragment : Fragment() {


    companion object {
        fun newInstance() = RecyclerFragment()
    }

    private lateinit var viewModel: RecyclerViewModel
    private lateinit var bindin: RecyclerFragmentBinding
    private lateinit var listas:List<ReposProperty>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindin= DataBindingUtil.inflate(inflater,
            R.layout.recycler_fragment,container,false)
        var RecyclerView: RecyclerView


        var adaptador= Adapter(context!!)
        viewModel= ViewModelProviders.of(activity!!).get(RecyclerViewModel::class.java)


        RecyclerView=bindin.recycler
        var strings=arguments?.getString("comentario")
        Toast.makeText(activity,"$strings",Toast.LENGTH_SHORT).show()

        RecyclerView.adapter=adaptador
        RecyclerView.layoutManager= LinearLayoutManager(context) as RecyclerView.LayoutManager?
        bindin.lifecycleOwner=viewLifecycleOwner

        viewModel.valor= strings.toString()
        viewModel.getReposProperties()

        viewModel.responsa.observe(this, Observer {
            listas=it
            adaptador.setQuestions(listas)
        })




        return bindin.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(RecyclerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
