package com.example.myjollyroots.customer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myjollyroots.Database.CustomerDatabse
import com.example.myjollyroots.Database.CustomerRepository
import com.example.myjollyroots.Model.Customer
import com.example.myjollyroots.R
import com.example.myjollyroots.databinding.ActivityAddCustomerBinding
import com.example.myjollyroots.home.HomeActivity
import com.example.myjollyroots.home.View.CustomerViewModel
import com.example.myjollyroots.home.View.CustomerViewModelFactory
import kotlin.properties.Delegates

class AddCustomerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCustomerBinding
    private lateinit var viewModel: CustomerViewModel
    private lateinit var name:EditText
    private lateinit var email:EditText
    private lateinit var phone:EditText
    private lateinit var address:EditText
    private lateinit var pass:EditText
    private lateinit var n:String
    private lateinit var e:String
    private lateinit var m:String
    private lateinit var add:String
    private lateinit var ps:String
    private lateinit var i:Intent
    private  var id=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()

        address = binding.addressEdt
        email = binding.emailEdt
        phone = binding.phoneEdt
        name = binding.nameEdt
        pass = binding.passwordEdt
        i = intent

        if (i.getIntExtra("flag", 0) == 0) {
            binding.button.setOnClickListener {
            insertall(0)

            }

        }
        else if (i.getIntExtra("flag", 0) == 1) {
            getsetdata()
            binding.button.text = "Update"
            binding.button.setOnClickListener {
                insertall(1)
            }
        }
        }

    private fun getsetdata() {
        if (i.hasExtra("id")&&i.hasExtra("name")&&i.hasExtra("email")&&i.hasExtra("password")&&i.hasExtra("phone")){
            n=i.getStringExtra("name").toString()
            e=i.getStringExtra("email").toString()
            m=i.getStringExtra("phone").toString()
            ps=i.getStringExtra("password").toString()
            add=i.getStringExtra("address").toString()
            id=i.getIntExtra("id",0)

            name.setText(n)
            email.setText(e)
            pass.setText(ps)
            phone.setText(m)
            address.setText(add)
        }
    }

    fun setup() {
        var f=CustomerViewModelFactory(CustomerRepository(CustomerDatabse.getInstanceDatabase(this).customerDao()))
        viewModel=ViewModelProvider(this,f)[CustomerViewModel::class.java]
    }


    private fun insertall(f:Int) {
        n=name.text.toString()
        e=email.text.toString()
        ps=pass.text.toString()
        add=address.text.toString()
        m=phone.text.toString()
        if(n!="" && e!="" && ps!=""&& add!=""&& m!="") {
            var customer = Customer(id, n, e, ps, m, add)
            if (f==0) {
                viewModel.insert(customer)
                Toast.makeText(this, "Added!!", Toast.LENGTH_SHORT).show()
            }
            else if (f==1){
                viewModel.update(customer)
                Toast.makeText(this, "Updated!!", Toast.LENGTH_SHORT).show()

            }

            //viewModel.getall()
            var i =Intent(this,HomeActivity::class.java)
            startActivityForResult(i,1)
            finish()
        }
        else{
            Toast.makeText(this, "Please enter all filled!!", Toast.LENGTH_SHORT).show()

        }

    }
}
