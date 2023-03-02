package com.example.gymapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.database.SubscribersDatabase
import com.example.gymapp.databinding.PaymentItemBinding
import com.example.gymapp.model.Payment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PaymentAdapter(private val context: Context , private val payments : ArrayList<Payment>) : RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PaymentItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val payment = payments[position]

        holder.name.text = payment.name
        holder.date.text = "PAYED IN: "+payment.subDate
        holder.price.text = "PAYED: "+ payment.subPrice+" $"

        holder.delete.setOnClickListener {
            //val application = context
            val payDao = SubscribersDatabase.getInstance(context).paymentDao
            GlobalScope.launch {
                payDao.delete(payment)
            }
            payments.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int {
       return payments.size
    }

    class ViewHolder(binding : PaymentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.subName
        val date = binding.subDate
        val price = binding.subPrice
        val delete = binding.deleteButton

    }
}