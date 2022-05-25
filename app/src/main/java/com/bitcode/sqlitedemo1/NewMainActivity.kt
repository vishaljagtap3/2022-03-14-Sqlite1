package com.bitcode.sqlitedemo1

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NewMainActivity : AppCompatActivity() {

    private lateinit var db : SQLiteDatabase
    private lateinit var dbUtil: DbUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*db = ProductsDBHelper(this, "db_products", null, 1)
            .writableDatabase*/

        dbUtil = DbUtil.getInstance(this)
        //dbUtil.addProduct()


    }
}