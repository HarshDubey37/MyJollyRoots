package com.example.myjollyroots

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjollyroots.Database.AdminRepository
import com.example.myjollyroots.Model.Admin
import kotlinx.coroutines.launch

class MainViewModel(val repo:AdminRepository) :ViewModel(){

    private val test=MutableLiveData<List<Admin>>()
    val data=test

    private val kl=MutableLiveData<Boolean>()
    var adminAdded=kl

    fun getalladmin(){
        viewModelScope.launch {
            data.value=repo.getAlladmin()
        }
    }

    fun addAdmin(admin: Admin){
        viewModelScope.launch {
            repo.insertadmin(admin)
            kl.value=true
        }
    }
}