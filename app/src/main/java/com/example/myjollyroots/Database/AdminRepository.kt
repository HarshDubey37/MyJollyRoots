package com.example.myjollyroots.Database

import com.example.myjollyroots.Model.Admin

class AdminRepository(val adminDao: AdminDao) {

   suspend fun getAlladmin():List<Admin>{
       return adminDao.getalladmin()
   }

    suspend fun getallreal(email:String,pass:String):List<Admin>{
        return adminDao.getrealadmin(email,pass)
    }

    suspend fun insertadmin(admin: Admin)=adminDao.insertadmin(admin)
}