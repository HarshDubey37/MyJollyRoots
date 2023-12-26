package com.example.myjollyroots.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myjollyroots.Model.Customer
import com.example.myjollyroots.customer.AddCustomerActivity
import com.example.myjollyroots.databinding.ItemCustomerBinding

class CustomerAdapter(
    var list:List<Customer>,var deleteButton: OnDeleteButton,var context:HomeActivity
):RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {

    class CustomerViewHolder(var binding: ItemCustomerBinding):ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        return CustomerViewHolder(ItemCustomerBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        var cl=list[position]
      holder.binding.name.text=cl.name
      holder.binding.mobile.text=cl.phone
      holder.binding.add.text=cl.address
      holder.binding.deletebtn.setOnClickListener {

            deleteButton.OnDeleteButtonClick(cl)

      }

       holder.binding.editbtn.setOnClickListener {
           var i=Intent(context,AddCustomerActivity::class.java)
           i.putExtra("id",cl.id)
           i.putExtra("flag",1)
           i.putExtra("name",cl.name)
           i.putExtra("phone",cl.phone)
           i.putExtra("email",cl.email)
           i.putExtra("password",cl.pass)
           i.putExtra("address",cl.address)
           context.startActivityForResult(i,1)
          // context.startActivity(i)
       }
    }
}