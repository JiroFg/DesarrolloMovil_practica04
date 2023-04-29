package com.example.practica04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.ArrayAdapter
import com.example.practica04.databinding.ActivityCancionesListBinding

class CancionesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCancionesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCancionesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = CancionesHelper(this)
        val db = dbHelper.readableDatabase

        val cursor = db.query(CancionesContract.FeedEntry.TABLE_NAME,
        null,
        null,
        null,
        null,
        null,
        null)

        val itemIds = mutableListOf<String>()
        with(cursor){
            while(moveToNext()) {
                val cancion = getString(getColumnIndexOrThrow(BaseColumns._ID)) + " " +
                        getString(getColumnIndexOrThrow(CancionesContract.FeedEntry.COLUMN_NAME_TITLE)) + " " +
                        getString(getColumnIndexOrThrow(CancionesContract.FeedEntry.COLUMN_NAME_ARTIST))
                itemIds.add(cancion)
            }
        }
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,itemIds)
        binding.cancionesList.adapter = arrayAdapter
    }
}