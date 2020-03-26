package com.java.imagewithtextstoreinroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "MyForms")
class FormEntity : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "fname")
    var fname: String? = null

    @ColumnInfo(name = "faddress")
    var faddress: String? = null

    @ColumnInfo(name = "fphno")
    var fphno: String? = null

}