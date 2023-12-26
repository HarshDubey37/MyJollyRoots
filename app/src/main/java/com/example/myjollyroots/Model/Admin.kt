package com.example.myjollyroots.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Admin")
data class Admin (

    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    val userid:String,
    val password:String,
)