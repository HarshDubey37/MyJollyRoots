package com.example.myjollyroots.customer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myjollyroots.Database.AdminRepository
import com.example.myjollyroots.Database.CustomerDatabse
import com.example.myjollyroots.MainActivity
import com.example.myjollyroots.databinding.ActivityCustomerBinding
import com.example.myjollyroots.home.HomeActivity

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerBinding
    private lateinit var viewModel: AdminViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("TAG","line 0 22")

        setup()
        Log.d("TAG","line 0 25")

        binding.login.setOnClickListener {

            val pref=getSharedPreferences("login",0)
            val ed=pref.edit()
            ed.putBoolean("login",true)
            ed.apply()

            val email=binding.emailTV.text.toString()
            val password=binding.passwordTV.text.toString()
            viewModel.getallreal(email,password)
            viewModel.admincheck.observe(this){
                if (it.isNotEmpty()){
                    val i=Intent(this,HomeActivity::class.java)
                    Toast.makeText(this,"Login Success!!",Toast.LENGTH_SHORT).show()
                    startActivity(i)
                }else{
                    Toast.makeText(this,"Enter Correct Details!!",Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    private fun setup() {
        val f=AdminViewModelFactory(AdminRepository(CustomerDatabse.getInstanceDatabase(this).adminDao()))
        viewModel=ViewModelProvider(this,f)[AdminViewModel::class.java]
    }
}