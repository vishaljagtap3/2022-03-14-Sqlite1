package com.bitcode.sqlitedemo1

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log

class EComProvider : ContentProvider() {

    private lateinit var db : SQLiteDatabase
    private var uriMatcher = UriMatcher(-1)

    init {
        uriMatcher.addURI("com.bitcode.ecomprovider", "products", 1)
        uriMatcher.addURI("com.bitcode.ecomprovider", "products/#/#", 2)
        uriMatcher.addURI("com.bitcode.ecomprovider", "products/#", 4)
        uriMatcher.addURI("com.bitcode.ecomprovider", "customers", 3)
    }

    override fun onCreate(): Boolean {
        mt("EComProvider Activated")

        db = ProductsDBHelper(context!!, "db_products", null, 1)
            .writableDatabase

        return true
    }

    private fun mt(text : String) {
        Log.e("tag", text)
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        when(uriMatcher.match(uri)) {
            1 -> return getAllProducts(uri)
            2 -> return getProductsInRange(uri)
            3 -> return getAllCustomers(uri)
            4 -> return getProductById(uri)
            -1 -> return null
        }

        return null
    }

    private fun getProductById(uri: Uri) : Cursor{
        return db.query(
            "products",
            null,
            "_id = ?",
            arrayOf(uri.pathSegments[1]),
            null,
            null,
            null
        )
    }

    private fun getAllProducts(uri : Uri) : Cursor{
        return db.query(
            "products",
            null,
            null,
            null,
            null,
            null,
            null
        )
    }

    private fun getProductsInRange(uri : Uri) : Cursor {
        return db.query(
            "products",
            null,
            "price > ? and price < ?",
            arrayOf(uri.pathSegments[1], uri.pathSegments[2]),
            null,
            null,
            null
        )
    }

    private fun getAllCustomers(uri : Uri) : Cursor{
        return db.query(
            "customers",
            null,
            null,
            null,
            null,
            null,
            null
        )
    }

   /* override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {

        if(uri.pathSegments[0].equals("products")) {
            if(uri.pathSegments.size == 1) {
                return db.query(
                    "products",
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                )
            }
            if(uri.pathSegments.size == 3) {
                return db.query(
                    "products",
                    projection,
                    "price > ? and price < ?",
                    arrayOf(uri.pathSegments[1], uri.pathSegments[2]),
                    null,
                    null,
                    sortOrder
                )
            }
        }

        if(uri.pathSegments[0].equals("customers")) {
            return db.query("customers", projection, selection, selectionArgs, null, null, sortOrder)
        }

        return null
    }*/

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when(uriMatcher.match(uri)) {
            1 -> return insertProduct(uri, values!!)
            3 -> return insertCustomer(uri, values!!)
        }

        return null
    }

    private fun insertCustomer(uri : Uri, values: ContentValues) : Uri? {
        var rowNum = db.insert("customers", null, values)
        if(rowNum == -1L) {
            return null
        }
        return Uri.withAppendedPath(uri, "" + values.getAsInteger("_id") )
    }

    private fun insertProduct(uri : Uri, values: ContentValues) : Uri? {
        var rowNum = db.insert("products", null, values)
        if(rowNum == -1L) {
            return null
        }
        return Uri.withAppendedPath(uri, "" + values.getAsInteger("_id") )
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }
}