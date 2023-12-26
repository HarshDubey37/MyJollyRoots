package com.example.myjollyroots

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myjollyroots.Database.AdminRepository

class MainViewModelFactory(val repo:AdminRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}