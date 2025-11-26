package com.example.green_ev2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NoticiaAdapter(
    private val listaNoticias: List<Noticia>,
    private val onItemClick: (Noticia) -> Unit
) : RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder>() {

    class NoticiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtResumen: TextView = itemView.findViewById(R.id.txtResumen)
        val imgPreview: ImageView = itemView.findViewById(R.id.imgPreview)
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

        // Mostrar la mini imagen SOLO si la URL existe
        if (!noticia.imagenUrl.isNullOrEmpty()) {
            holder.imgPreview.visibility = View.VISIBLE
            Glide.with(holder.itemView.context)
                .load(noticia.imagenUrl)
                .into(holder.imgPreview)
        } else {
            // Si no hay imagen ocultamos el ImageView para evitar huecos feos
            holder.imgPreview.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            onItemClick(noticia)
        }
    }

    override fun getItemCount(): Int = listaNoticias.size
}
