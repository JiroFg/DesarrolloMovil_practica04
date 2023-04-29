package com.example.practica04

import android.provider.BaseColumns

object CancionesContract{
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "cancion"
        const val COLUMN_NAME_TITLE = "titulo"
        const val COLUMN_NAME_ARTIST = "artista"
    }
}