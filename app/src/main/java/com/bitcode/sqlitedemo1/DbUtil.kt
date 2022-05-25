package com.bitcode.sqlitedemo1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteCursorDriver
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQuery

class DbUtil(context: Context) {

    companion object {
        private var dbUtil : DbUtil? = null
        var DB_NAME = "db_products"

        fun getInstance(context: Context) : DbUtil {
            if(dbUtil == null) {
                dbUtil = DbUtil(context)
            }
            return dbUtil!!
        }
    }

    private lateinit var db : SQLiteDatabase

    init {
        db = ProductsDBHelper(context, DB_NAME, null, 1)
            .writableDatabase
    }

    fun addProduct(id : Int, title : String, price : Int) : Long {
        var values = ContentValues()
        values.put("_id", 101)
        values.put("title", "Laptop")
        values.put("price", 1000)

        return db.insert("products", null, values)

    }

    /*fun getAllProducts() : ArrayList<Product> {

    }*/
}

class MyCursorFactory : SQLiteDatabase.CursorFactory {
    override fun newCursor(
        db: SQLiteDatabase?,
        masterQuery: SQLiteCursorDriver?,
        editTable: String?,
        query: SQLiteQuery?
    ): Cursor {
        TODO("Not yet implemented")
    }

}