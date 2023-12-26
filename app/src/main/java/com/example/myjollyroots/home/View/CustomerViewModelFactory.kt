package com.example.myjollyroots.home.View

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myjollyroots.Database.CustomerRepository

class CustomerViewModelFactory(private var customerRepo:CustomerRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CustomerViewModel(customerRepo) as T
    }


}