package com.example.green_ev2

import java.io.Serializable

data class Noticia(
    val autor: String = "",
    val contenido: String = "",
    val fecha: String = "",
    val imagenUrl: String = "",
    val resumen: String = "",
    val titulo: String = "",
) : Serializable
