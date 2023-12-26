package com.example.myjollyroots.home.View

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjollyroots.Database.CustomerRepository
import com.example.myjollyroots.Model.Customer
import kotlinx.coroutines.launch

class CustomerViewModel(var customerRepo: CustomerRepository):ViewModel() {

    private var data=MutableLiveData<List<Customer>>()

    var customerlist=data

    fun getall() {
           viewModelScope.launch {
               data.value=customerRepo.getCustomer()
           }
    }

    fun insert(clist:Customer) {
        viewModelScope.launch {
            customerRepo.insertCustomer(clist)
        }
    }
      fun update(clist:Customer){
        viewModelScope.launch {
            customerRepo.updateCustomer(clist)
        }
    }
        fun delete(clist:Customer){
        viewModelScope.launch {
            customerRepo.deleteCustomer(clist )
        }
    }

    fun deleteall(){
        viewModelScope.launch {
            customerRepo.deleteAll()
        }
    }
}