package com.example.diamory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ContentView : AppCompatActivity() {

    val dbHandler = SQLiteHelper(this)

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
                return cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_TEXT)) as String
            }
        }

        return "haista ite"
    }
}
