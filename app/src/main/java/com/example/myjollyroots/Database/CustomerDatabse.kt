package com.example.myjollyroots.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myjollyroots.Model.Admin
import com.example.myjollyroots.Model.Customer


@Database(entities = [Customer::class,Admin::class], version = 1)
abstract class CustomerDatabse : RoomDatabase() {

        abstract fun customerDao() : CustomerDao

        abstract fun adminDao() : AdminDao

        companion object{

            private  var INSTANCE:CustomerDatabse?=null

            fun getInstanceDatabase(context: Context):CustomerDatabse{

                if (INSTANCE!=null)
                    return INSTANCE as CustomerDatabse
                else{
                    synchronized(context){

                        var instance = Room.databaseBuilder(
                            context,CustomerDatabse::class.java,"Customer_DataBase")
                            .build()
                        INSTANCE=instance
                        return INSTANCE as CustomerDatabse

                    }
                }

            }
        }
}