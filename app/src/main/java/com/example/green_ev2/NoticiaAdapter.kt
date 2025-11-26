package com.example.green_ev2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoticiaAdapter(
    private val listaNoticias: List<Noticia>,
    private val onItemClick: (Noticia) -> Unit
) : RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder>() {

    class NoticiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtResumen: TextView = itemView.findViewById(R.id.txtResumen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_noticia, parent, false)
        return NoticiaViewHolder(vista)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val noticia = listaNoticias[position]

        holder.txtTitulo.text = noticia.titulo
        holder.txtResumen.text = noticia.resumen

        holder.itemView.setOnClickListener {
            onItemClick(noticia)
        }
    }

    override fun getItemCount(): Int = listaNoticias.size
}
