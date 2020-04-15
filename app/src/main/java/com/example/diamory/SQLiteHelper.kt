package com.example.diamory

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION )  {

    companion object{
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "DiaryDatabase"
        val TABLE_NAME = "DiaryTable"
        val KEY_ID = "id"
        val KEY_DATE = "date"
        val KEY_TEXT = "text"
        val KEY_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_DIARY_TABLE = ("CREATE TABLE " +
                TABLE_NAME +
                "(" + KEY_ID +
                "INTEGER PRIMARY KEY, " +
                KEY_DATE + "TEXT, " +
                KEY_TEXT + "TEXT," + "" +
                KEY_IMAGE + "BLOB" + ")")
        db?.execSQL(CREATE_DIARY_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)
    }

    //Insert new values to table
    fun addLog(diary: DiaryModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_ID, diary.textID)
        values.put(KEY_DATE, diary.date)
        values.put(KEY_TEXT, diary.diaryText)
        values.put(KEY_IMAGE, diary.image)
        db.insert(TABLE_NAME, null, values)
        db.close()

    }

    fun getAllLogs(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT KEY_TEXT FROM $TABLE_NAME", null)
    }
}