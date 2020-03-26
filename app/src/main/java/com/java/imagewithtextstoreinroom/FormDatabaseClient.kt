package com.java.imagewithtextstoreinroom

import android.content.Context
import androidx.room.Room

class FormDatabaseClient(mContex: Context) {

    private var formDataBase: FormDataBase
    private val mContex: Context
    private var mInstance: FormDatabaseClient? = null

    init {
        this.mContex = mContex

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        formDataBase = Room.databaseBuilder(mContex, FormDataBase::class.java, "MyToDos").build()
    }

    @Synchronized
    fun getInstance(mCtx: Context): FormDatabaseClient {
        if (mInstance == null) {
            mInstance = FormDatabaseClient(mContex)
        }
        return mInstance as FormDatabaseClient
    }

    fun getFormAppDatabase(): FormDataBase {
        return formDataBase
    }

}