package com.example.myjollyroots.home

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myjollyroots.Database.CustomerDatabse
import com.example.myjollyroots.Database.CustomerRepository
import com.example.myjollyroots.MainActivity
import com.example.myjollyroots.Model.Customer
import com.example.myjollyroots.R
import com.example.myjollyroots.customer.AddCustomerActivity
import com.example.myjollyroots.customer.AdminActivity
import com.example.myjollyroots.databinding.ActivityHomeBinding
import com.example.myjollyroots.home.View.CustomerViewModel
import com.example.myjollyroots.home.View.CustomerViewModelFactory

class HomeActivity : AppCompatActivity(), OnDeleteButton {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: CustomerViewModel
    private var list: List<Customer>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
        setSupportActionBar(binding.toolbar)
      supportActionBar?.title="   Home Page"
        binding.addnewcuBTN.setOnClickListener {
            val i = Intent(this, AddCustomerActivity::class.java)
            startActivity(i)
        }
        binding.addTV.setOnClickListener {
            val i = Intent(this, AddCustomerActivity::class.java)
            startActivity(i)
        }

        getallcustomershow()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.logoutBTN ->
                logout()

            R.id.deleteall ->
                confirmall()
        }
        return true
    }

    private fun logout() {

        val builder = AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { di: DialogInterface?, i: Int ->
                val pref = getSharedPreferences("login", 0)
                val editor = pref.edit()
                editor.putBoolean("login", false)
                editor.apply()
                val i = Intent(this, AdminActivity::class.java)
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                startActivity(i)
            }
            .setNegativeButton("No") { dli: DialogInterface, i: Int ->
                Toast.makeText(this, "Good!!", Toast.LENGTH_SHORT).show()
            }
            .create().show()

    }

    fun confirmall() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete All")
        builder.setMessage("Are you sure you want to delete all Data??")
        builder.setPositiveButton("YES") { dialogInterface: DialogInterface?, i: Int ->
            if (list!!.isNotEmpty()) {
                viewModel.deleteall()
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(this, "List is Empty", Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton(
            "NO"
        ) { dialogInterface, i ->
            Toast.makeText(this, "Safe", Toast.LENGTH_SHORT).show()
        }
        builder.create().show()
    }

    private fun getallcustomershow() {
        viewModel.getall()
        viewModel.customerlist.observe(this) {
            list = it
            if (it.size == 0) {
                binding.itemRV.visibility = View.GONE
                binding.addcustomerTV.visibility = View.VISIBLE
                binding.nocustomerTV.visibility = View.VISIBLE
                binding.addnewcuBTN.visibility = View.VISIBLE
                binding.layoutaddcust.visibility = View.GONE
                //Toast.makeText(this, "size " + it.size, Toast.LENGTH_SHORT).show()

            } else {
                val adapter = CustomerAdapter(it, this, this)
                binding.itemRV.visibility = View.VISIBLE
                binding.addcustomerTV.visibility = View.GONE
                binding.layoutaddcust.visibility = View.VISIBLE
                binding.nocustomerTV.visibility = View.GONE
                binding.addnewcuBTN.visibility = View.GONE
               // Toast.makeText(this, "size " + it.size, Toast.LENGTH_SHORT).show()
                binding.itemRV.adapter = adapter
                binding.itemRV.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.itemRV.setHasFixedSize(true)
                binding.itemRV.addItemDecoration(DividerItemDecoration(this, 1))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1)
            recreate()
    }

    private fun setup() {
        val f = CustomerViewModelFactory(
            CustomerRepository(
                CustomerDatabse.getInstanceDatabase(this).customerDao()
            )
        )
        viewModel = ViewModelProvider(this, f)[CustomerViewModel::class.java]
    }

    override fun OnDeleteButtonClick(customer: Customer) {
        confirm(customer)
    }

    private fun confirm(customer: Customer) {
       AlertDialog.Builder(this)
            .setTitle("Delete")
            .setMessage("Are you sure you want to delete this item?")
            .setPositiveButton("Yes") { dl:DialogInterface, i:Int ->
                viewModel.delete(customer)
                getallcustomershow()
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){
               dl: DialogInterface,i:Int->
                Toast.makeText(this, "Safe", Toast.LENGTH_SHORT).show()
            }.create().show()
    }
}