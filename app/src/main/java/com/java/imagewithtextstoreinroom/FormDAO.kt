package com.java.imagewithtextstoreinroom

import androidx.room.*

@Dao
interface FormDAO {
    @Query("SELECT * FROM MyForms")
    fun getAllForms(): List<FormEntity>

    @Insert
    fun insert(formEntity: FormEntity)

    @Delete
    fun delete(formEntity: FormEntity)

    @Update
    fun update(formEntity: FormEntity)
}