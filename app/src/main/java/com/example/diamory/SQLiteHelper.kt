package com.example.diamory

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION )  {

    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "DiaryDatabase"
        private val TABLE_NAME = "DiaryTable"
        private val KEY_ID = "id"
        private val KEY_DATE = "date"
        private val KEY_TEXT = "text"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_DIARY_TABLE = ("CREATE TABLE " +
                TABLE_NAME +
                "(" + KEY_ID +
                "INTEGER PRIMARY KEY" +
                KEY_DATE + "TEXT" +
                KEY_TEXT + "TEXT")
        db?.execSQL(CREATE_DIARY_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
    }

    //Insert new values to table
    fun addLog(diarymodel: DiaryModelClass){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_ID, diarymodel.textID)
        values.put(KEY_DATE, diarymodel.date)
        values.put(KEY_TEXT, diarymodel.diaryText)
        db.insert(TABLE_NAME, null, values)
        db.close()

    }

    fun getAllLogs(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
}