package com.example.diamory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_log.setOnClickListener{
            val intent = Intent(applicationContext, LogActivity::class.java)
            startActivity(intent)

        button_diary.setOnClickListener{
            val intent = Intent(applicationContext, DiaryActivity::class.java)
            startActivity(intent)
        }
        }
    }


}
