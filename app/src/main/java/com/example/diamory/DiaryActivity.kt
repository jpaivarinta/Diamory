package com.example.diamory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_diary.*
import java.time.format.DateTimeFormatter

class DiaryActivity : AppCompatActivity() {

    lateinit var listView: ListView
    private var items = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_diary)


        listView = findViewById<ListView>(R.id.dbListview)
        items = createList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.setOnItemClickListener{ parent, view, position, id ->

            val selectedItem = parent.getItemAtPosition(position) as String
            val intent = Intent(applicationContext, ContentView::class.java)
            intent.putExtra("diarydate", selectedItem)
            startActivity(intent)

        }


    }

    private fun createList(): ArrayList<String> {
        var tempList = arrayListOf<String>()
        val dbHandler = SQLiteHelper(this)
        val
        cursor = dbHandler.getAllLogs()
        cursor!!.moveToFirst()
        tempList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)))
        while (cursor.moveToNext()) {
            tempList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)))
        }
        cursor.close()
        return tempList
    }


}

