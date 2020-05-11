package com.example.diamory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_content_view.*

class ContentView : AppCompatActivity() {

    val dbHandler = SQLiteHelper(this)
    var dateId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_view)


        val dateTextview: TextView = findViewById(R.id.dateTextView) as TextView
        val diaryTextview: TextView = findViewById(R.id.diarytextView) as TextView


        val intent = intent
        val diarydate = intent.getStringExtra("diarydate")
        val diarytext = createString(diarydate)
        dateTextview.setText(diarydate)
        diaryTextview.setText(diarytext)

        button_delete.setOnClickListener{
            dbHandler.deleteLog(dateId)
            val intent = Intent(applicationContext, DiaryActivity::class.java)
            startActivity(intent)
        }


    }

    private fun createString(date: String): String {
        val dbHandler = SQLiteHelper(this)
        val cursor = dbHandler.getAllLogs()
        cursor!!.moveToFirst()
        if (cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)) == date){
            return cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_TEXT)) as String
        }
        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)) == date){
                dateId = cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_ID)) as String
                return cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_TEXT)) as String
            }
        }

        return "Log not found"
    }


}
