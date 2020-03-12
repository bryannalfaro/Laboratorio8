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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        val itemView=inflater.inflate(R.layout.item_list,parent,false)
        return ViewHolderData((itemView))
    }

    override fun getItemCount()=PreguntasList.size

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        val pregunta=PreguntasList[position]

        holder.questionItenView.text=pregunta.get(position).name
        holder.pregunta.text=pregunta.get(position).description
    }


    internal fun setQuestions(preguntas: ArrayList<ArrayList<ReposProperty>>){
        this.PreguntasList=preguntas

        notifyDataSetChanged()
    }

    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private var PreguntasList= ArrayList<ArrayList<ReposProperty>>()

    //ViewHolder for the data
    inner class ViewHolderData(itemView: View): RecyclerView.ViewHolder(itemView){
        val questionItenView: TextView =itemView.findViewById(R.id.repositorio_name)
        val pregunta: TextView =itemView.findViewById(R.id.description_repositorio)
    }



}