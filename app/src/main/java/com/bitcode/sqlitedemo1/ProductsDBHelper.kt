package com.bitcode.sqlitedemo1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductsDBHelper(
    context: Context,
    dbName : String,
    factory : SQLiteDatabase.CursorFactory?,
    version : Int
) : SQLiteOpenHelper(
    context,
    dbName,
    factory,
    version
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table products(_id integer primary key, title text not null,  price integer)")
        db!!.execSQL("create table customers(phone integer primary key, name text, city text)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }
}