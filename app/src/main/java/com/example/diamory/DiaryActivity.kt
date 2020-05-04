package com.example.diamory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_diary.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DiaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_diary)
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")

    btnAddToDb.setOnClickListener {
        val dbHandler =SQLiteHelper(this)
        val log = etName.text.toString()
        val currentTime = LocalDateTime.now().format(formatter).toString()
        dbHandler.addLog(DiaryModel(log, currentTime))
        Toast.makeText(this, "New text added to database", Toast.LENGTH_LONG).show()
    }
    btnShowDatafromDb.setOnClickListener {

        tvDisplayName.text = ""
        val dbHandler = SQLiteHelper(this)
        val cursor = dbHandler.getAllLogs()
        cursor!!.moveToFirst()
        tvDisplayName.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_TEXT)))
        tvDisplayName.append(" ")
        tvDisplayName.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)))
        tvDisplayName.append("\n")
        //tvDisplayName.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)))
        while (cursor.moveToNext()) {
            tvDisplayName.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_TEXT)))
            tvDisplayName.append(" ")
            tvDisplayName.append(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)))
            tvDisplayName.append("\n")
        }
        cursor.close()
        }


    }


}

