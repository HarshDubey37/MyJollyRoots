package com.example.myjollyroots.Database

import com.example.myjollyroots.Model.Customer

class CustomerRepository(
    private val customerDao:CustomerDao) {

    suspend fun insertCustomer(customer: Customer){
        customerDao.insertCustomer(customer)
    }

    suspend fun updateCustomer(customer: Customer){
        customerDao.updateCustomer(customer)
    }

    suspend fun deleteCustomer(customer: Customer){
        customerDao.deleteCustomer(customer)
    }

    suspend fun getCustomer():List<Customer>
    {
        return customerDao.getCustomer()
    }

    suspend fun deleteAll(){
        customerDao.deleteAll()
    }
}