package com.example.laboratorio8

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio8.redes.ReposProperty

class Adapter internal constructor(context: Context): RecyclerView.Adapter<Adapter.ViewHolderData>(){

    private var inflater: LayoutInflater = LayoutInflater.from(context)
    lateinit var preguntasList:List<ReposProperty>
    var conteo=0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        val itemView=inflater.inflate(R.layout.item_list,parent,false)
        return ViewHolderData((itemView))
    }

    internal fun setQuestions(preguntas: List<ReposProperty>){
        preguntasList=preguntas
        conteo=preguntasList.size

        notifyDataSetChanged()
    }

    override fun getItemCount()=conteo

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        val pregunta=preguntasList[position]

        holder.questionItenView.text=pregunta.name

    }






    //ViewHolder for the data
    inner class ViewHolderData(itemView: View): RecyclerView.ViewHolder(itemView){
        val questionItenView: TextView =itemView.findViewById(R.id.repositorio_name)
        val pregunta: TextView =itemView.findViewById(R.id.description_repositorio)
    }



}