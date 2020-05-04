package com.example.diamory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_diary.*
import java.time.format.DateTimeFormatter

class DiaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_diary)

        tvDisplayDb.text = ""
        val dbHandler = SQLiteHelper(this)
        val cursor = dbHandler.getAllLogs()
        cursor!!.moveToFirst()
        tvDisplayDb.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_TEXT)))
        tvDisplayDb.append(" ")
        tvDisplayDb.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)))
        tvDisplayDb.append("\n")
        //tvDisplayName.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)))
        while (cursor.moveToNext()) {
            tvDisplayDb.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_TEXT)))
            tvDisplayDb.append(" ")
            tvDisplayDb.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)))
            tvDisplayDb.append("\n")
        }
        cursor.close()




    }


}

