package com.example.diamory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_diary.*
import java.time.format.DateTimeFormatter

class DiaryActivity : AppCompatActivity() {

    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_diary)

        listView = findViewById()


        tvDisplayDb.text = ""
        val dbHandler = SQLiteHelper(this)
        val cursor = dbHandler.getAllLogs()
        cursor!!.moveToFirst()
        tvDisplayDb.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_TEXT)))
        tvDisplayDb.append("\n")
        while (cursor.moveToNext()) {
            tvDisplayDb.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)))
            tvDisplayDb.append("\n")
        }
        cursor.close()


        /*
        tvDisplayDb.text = ""
        val dbHandler = SQLiteHelper(this)
        val cursor = dbHandler.getAllLogs()
        cursor!!.moveToFirst()
        tvDisplayDb.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_TEXT)))
        tvDisplayDb.append(" ")
        tvDisplayDb.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)))
        tvDisplayDb.append("\n")
        while (cursor.moveToNext()) {
            tvDisplayDb.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_TEXT)))
            tvDisplayDb.append(" ")
            tvDisplayDb.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)))
            tvDisplayDb.append("\n")
        }
        cursor.close()


         */
    }


}

