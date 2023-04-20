package com.example.practica04

import android.provider.BaseColumns

object CancionesContract{
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "Cancion"
        const val COLUMN_NAME_TITLE = "Titulo"
        const val COLUMN_NAME_ARTIST = "Artista"
    }
}