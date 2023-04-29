package com.example.practica04

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class CancionesHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_CANCIONES)

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_CANCIONES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Canciones.db"
        const val SQL_CREATE_CANCIONES = "CREATE TABLE ${CancionesContract.FeedEntry.TABLE_NAME} (" +
        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
        "${CancionesContract.FeedEntry.COLUMN_NAME_TITLE} TEXT," +
        "${CancionesContract.FeedEntry.COLUMN_NAME_ARTIST} TEXT)"
        const val SQL_DELETE_CANCIONES = "DROP TABLE IF EXISTS ${CancionesContract.FeedEntry.TABLE_NAME}"
    }
}