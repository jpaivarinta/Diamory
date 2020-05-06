package com.example.diamory

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION )  {



    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_DIARY_TABLE = ("CREATE TABLE " +
                TABLE_NAME +
                " (" + KEY_ID +
                " INTEGER PRIMARY KEY, " +
                KEY_DATE + " TEXT, " +
                KEY_TEXT + " TEXT, " +
                KEY_IMAGE + " BLOB" + ")")
        db?.execSQL(CREATE_DIARY_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    //Insert new values to table
    fun addLog(diary: DiaryModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_TEXT, diary.diaryText)
        values.put(KEY_DATE, diary.date)
        //values.put(KEY_IMAGE, diary.image)       //MUUTOKSIA
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun deleteLog(diary: DiaryModel){
        val db = this.writableDatabase
    }

    fun getAllLogs(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun getOneLog(date: String): String {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $KEY_DATE = $date", null) as String
    }

    fun getDates(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT KEY_DATE FROM $TABLE_NAME", null)
    }

    companion object{
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "DiaryDatabase.db"
        val TABLE_NAME = "DiaryTable"

        //TABLE
        val KEY_ID = "id"
        val KEY_DATE = "date"
        val KEY_TEXT = "text"
        val KEY_IMAGE = "image"
    }
}