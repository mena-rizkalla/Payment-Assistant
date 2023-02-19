package com.example.gymapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.databinding.PaymentItemBinding
import com.example.gymapp.model.Payment

class PaymentAdapter(private val context: Context , private val payments : List<Payment>) : RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PaymentItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val payment = payments[position]

        holder.name.text = payment.name
        holder.date.text = payment.subDate
        holder.price.text = payment.subPrice
    }

    override fun getItemCount(): Int {
       return payments.size
    }

    class ViewHolder(private val binding : PaymentItemBinding) : RecyclerView.ViewHolder(binding.root){
        val name = binding.subName
        val date = binding.subDate
        val price = binding.subPrice
    }
}