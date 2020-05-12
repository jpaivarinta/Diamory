package com.example.diamory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_diary.*


class DiaryActivity : AppCompatActivity() {

    lateinit var listView: ListView
    private var items = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)
        val emptyText: TextView = findViewById(R.id.textView2) as TextView
        val dbHandler = SQLiteHelper(this)

        listView = findViewById<ListView>(R.id.dbListview)
        var mcursor = dbHandler.getCount()
        mcursor!!.moveToFirst()
        if (mcursor.getInt(0) > 0) {
            items = createList()
        }
        else {
            emptyText.setText("NO LOGS")
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.setOnItemClickListener{ parent, view, position, id ->

            val selectedItem = parent.getItemAtPosition(position) as String
            val intent = Intent(applicationContext, ContentView::class.java)
            intent.putExtra("diarydate", selectedItem)
            startActivity(intent)

        }

        button_main.setOnClickListener{
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun createList(): ArrayList<String> {
        var tempList = arrayListOf<String>()
        val dbHandler = SQLiteHelper(this)
        val cursor = dbHandler.getAllLogs()
        cursor!!.moveToFirst()
        tempList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)))
        while (cursor.moveToNext()) {
            tempList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)))
        }
        cursor.close()
        return tempList
    }


}