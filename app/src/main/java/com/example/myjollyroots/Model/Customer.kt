package com.example.myjollyroots.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer")
data class Customer (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val name:String,
    val email:String,
    val pass:String,
    val phone:String,
    val address:String
)