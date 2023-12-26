package com.example.myjollyroots.customer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjollyroots.Database.AdminRepository
import com.example.myjollyroots.Model.Admin
import kotlinx.coroutines.launch

class AdminViewModel(private val repo:AdminRepository) :ViewModel(){

   private val _admincheck= MutableLiveData<List<Admin>>()
    var admincheck=_admincheck


    fun getallreal(email: String,pass:String){
        viewModelScope.launch {
          _admincheck.value=repo.getallreal(email, pass)
        }
    }
}