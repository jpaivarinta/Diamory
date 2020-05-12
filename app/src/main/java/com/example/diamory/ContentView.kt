package com.example.diamory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.MovementMethod
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_content_view.*

class ContentView : AppCompatActivity() {

    val dbHandler = SQLiteHelper(this)
    var dateId = ""
    lateinit var image: ByteArray


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_view)


        val dateTextview: TextView = findViewById(R.id.dateTextView) as TextView
        val diaryTextview: TextView = findViewById(R.id.diarytextView) as TextView
        val imageView: ImageView = findViewById(R.id.imageView2) as ImageView

        val intent = intent
        val diarydate = intent.getStringExtra("diarydate")
        image = byteArrayOf()
        val diarytext = createString(diarydate)
        dateTextview.setText(diarydate)
        diaryTextview.setText(diarytext)
        diaryTextview.movementMethod = ScrollingMovementMethod()

        if (image.isNotEmpty()){
            var bmImage = Tools.getImage(image)
            imageView.setImageBitmap(bmImage)
        }

        button_delete.setOnClickListener{
            dbHandler.deleteLog(dateId)
            val intent = Intent(applicationContext, DiaryActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Log on date '" + diarydate + "' deleted.", Toast.LENGTH_LONG).show()
        }

        button_main2.setOnClickListener{
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createString(date: String): String {
        val dbHandler = SQLiteHelper(this)
        val cursor = dbHandler.getAllLogs()
        cursor!!.moveToFirst()
        if (cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)) == date){
            dateId = cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_ID)) as String
            if (cursor.getBlob(cursor.getColumnIndex(SQLiteHelper.KEY_IMAGE)) != null){
                image = cursor.getBlob(cursor.getColumnIndex(SQLiteHelper.KEY_IMAGE))
            }
            return cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_TEXT)) as String
        }
        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATE)) == date){
                dateId = cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_ID)) as String
                if (cursor.getBlob(cursor.getColumnIndex(SQLiteHelper.KEY_IMAGE)) != null){
                    image = cursor.getBlob(cursor.getColumnIndex(SQLiteHelper.KEY_IMAGE))
                }
                return cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_TEXT)) as String
            }
        }
        return "Log not found"
    }

}

