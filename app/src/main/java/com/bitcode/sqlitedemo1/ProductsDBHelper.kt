package com.bitcode.sqlitedemo1

import android.content.ContentValues
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

        insertData(db)

    }

    private fun insertData(db : SQLiteDatabase) {
        var values = ContentValues()

        values.put("_id", 101)
        values.put("title", "Laptop")
        values.put("price", 1000)
        var rowNum = db.insert("products", null, values)

        values.put("_id", 102)
        values.put("title", "Projector")
        values.put("price", 1200)
        rowNum = db.insert("products", null, values)

        values.put("_id", 103)
        values.put("title", "television")
        values.put("price", 1300)
        rowNum = db.insert("products", null, values)

        values.put("_id", 104)
        values.put("title", "Table")
        values.put("price", 1400)
        rowNum = db.insert("products", null, values)

        var customerValues = ContentValues()

        customerValues.put("phone", "1234")
        customerValues.put("name", "XYZ")
        customerValues.put("city", "Pune")
        db.insert("customers", null, customerValues)

        customerValues.put("phone", "5678")
        customerValues.put("name", "LMN")
        customerValues.put("city", "Mumbai")
        db.insert("customers", null, customerValues)

        customerValues.put("phone", "9812")
        customerValues.put("name", "PQR")
        customerValues.put("city", "NaGPUR")
        db.insert("customers", null, customerValues)

        customerValues.put("phone", "9087")
        customerValues.put("name", "JKL")
        customerValues.put("city", "Chennai")
        db.insert("customers", null, customerValues)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }
}