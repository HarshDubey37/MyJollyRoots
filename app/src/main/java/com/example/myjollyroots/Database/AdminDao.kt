package com.example.myjollyroots.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myjollyroots.Model.Admin


@Dao
interface AdminDao {

    @Insert
    suspend fun insertadmin(admin: Admin)

    @Query("SELECT * FROM Admin")
    suspend fun getalladmin():List<Admin>

    @Query("select * from Admin where userid=:email and password=:pass")
    suspend fun getrealadmin(email:String,pass:String):List<Admin>
}