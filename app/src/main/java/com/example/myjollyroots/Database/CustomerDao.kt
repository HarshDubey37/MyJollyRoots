package com.example.myjollyroots.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myjollyroots.Model.Customer

@Dao
interface CustomerDao {

    @Query("select * from Customer")
    suspend fun getCustomer():List<Customer>

    @Insert
    suspend fun insertCustomer(customer: Customer)

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Update
    suspend fun updateCustomer(customer: Customer)

    @Query("Delete from Customer")
    suspend fun deleteAll()
}