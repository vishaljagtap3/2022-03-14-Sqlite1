package com.bitcode.sqlitedemo1

import android.app.Activity
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDB()
        //insertRecords()
        populate()
        //populate1()
        mt("----------------------------------------")
        updateRecords()
        mt("----------------------------------------")
        populate()
        mt("----------------------------------------")
        deleteRecords()
        populate()
    }

    private fun deleteRecords() {
        var count = db.delete("products", "_id = ?", arrayOf("101"))
        if(count > 0 ) {
            mt("Data deleted: $count")
        }
        else {
            mt("Data not deleted: $count")
        }
    }

    private fun updateRecords() {
        var values = ContentValues()
        values.put("price", 1500)
        values.put("title", "New Laptop with Ubuntu")

        var count = db.update("products", values, "_id = ?", arrayOf("101"))
        if(count > 0) {
            mt("Data updated")
        }
        else {
            mt("Can not update data")
        }
    }

    private fun populate1() {
        var c : Cursor = db.rawQuery(
            "select _id, title, price from products where price > 1000 and price < 1300",
            null
        )

        while (c.moveToNext()) {
            mt("id = ${c.getInt(0)} title = ${c.getString(1)} price = ${c.getInt(2)}")
        }

        c.close()
    }




    private fun populate() {
        var c: Cursor =
            db.query(
                "products",
                null,
                null,
                null,
                null,
                null,
                "price desc"
            )

        while (c.moveToNext()) {
            mt("id = ${c.getInt(0)} title = ${c.getString(1)} price = ${c.getInt(2)}")
        }

        c.close()
    }

    private fun populateDummy() {
        var columns = arrayOf("_id", "title", "price")
        var whereClause = "price > ? and price < ?"
        var whereArgs = arrayOf("1000", "1300")
        var groupBy = "dept, city"
        var having = "sum(price) > 10000"
        var sortOrder = "price desc, title desc"

        db.query(
            "products",
            columns,
            whereClause,
            whereArgs,
            groupBy,
            having,
            sortOrder
        )
    }

    private fun insertRecords() {
        var values = ContentValues()
        values.put("_id", 101)
        values.put("title", "Laptop")
        values.put("price", 1000)

        var rowNum = db.insert("products", null, values)
        //var rowNum = db.insert("products", "dept, city", values)
        mt("insert res: $rowNum")

        rowNum = db.insert("products", null, values)
        mt("insert res: $rowNum")

        values.put("_id", 102)
        values.put("title", "Projector")
        values.put("price", 1200)
        rowNum = db.insert("products", null, values)
        mt("insert res: $rowNum")

        values.put("_id", 103)
        values.put("title", "television")
        values.put("price", 1300)
        rowNum = db.insert("products", null, values)
        mt("insert res: $rowNum")


        values.put("_id", 104)
        values.put("title", "Table")
        values.put("price", 1400)
        rowNum = db.insert("products", null, values)
        mt("insert res: $rowNum")
    }

    private fun mt(text: String) {
        Log.e("tag", text)
    }

    private fun initDB() {
        db = openOrCreateDatabase("db_products", Activity.MODE_PRIVATE, null)

        try {
            db.execSQL("create table products(_id integer primary key, title text not null,  price integer)")

            /*var args = arrayOf("customers", "customer_id")
            db.execSQL("create table cus (? integer primary key, name text)", args)*/
        } catch (e: Exception) {
        }

        //db.close()
    }
}