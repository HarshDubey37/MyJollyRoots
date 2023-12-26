package com.example.myjollyroots.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myjollyroots.Database.AdminRepository

class AdminViewModelFactory(val repository: AdminRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AdminViewModel(repository) as T
    }
}