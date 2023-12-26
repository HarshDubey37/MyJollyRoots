package com.example.myjollyroots

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myjollyroots.Database.AdminRepository
import com.example.myjollyroots.Database.CustomerDatabse
import com.example.myjollyroots.Model.Admin
import com.example.myjollyroots.customer.AdminActivity
import com.example.myjollyroots.databinding.ActivityMainBinding
import com.example.myjollyroots.home.HomeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setup()

        Handler(Looper.getMainLooper()).postDelayed({

            val pref=getSharedPreferences("login", MODE_PRIVATE)
            var check=pref.getBoolean("login",false)
            if (!check) {
            viewModel.getalladmin()
            viewModel.data.observe(this) {
                if (it.isNotEmpty()) {
                    val i = Intent(this, AdminActivity::class.java)
                    startActivity(i)
                    finish()
                } else {
                    viewModel.addAdmin(Admin(0, "dubeyharsh91221@gmail.com", "123123123"))
                    viewModel.adminAdded.observe(this) {
                        if (it) {
                            Log.d("TAG", "line 44")
                            val i = Intent(this, AdminActivity::class.java)
                            startActivity(i)
                            finish()
                        }
                    }
                }
            }
        }else{
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
            finish()
        }
       },1800
       )
    }


    private fun setup() {
        Log.d("TAG","line 63")

        var f=MainViewModelFactory(AdminRepository(CustomerDatabse.getInstanceDatabase(this).adminDao()))
        viewModel=ViewModelProvider(this,f)[MainViewModel::class.java]
    }
}