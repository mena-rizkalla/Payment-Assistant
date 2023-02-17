package com.example.gymapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.R
import com.example.gymapp.model.Payment

class PaymentAdapter(private val context: Context , private val payments : List<Payment>) : RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.payment_item , parent ,false)
        return PaymentAdapter.ViewHolder(view)
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.sub_name)
        val date = itemView.findViewById<TextView>(R.id.sub_date)
        val price = itemView.findViewById<TextView>(R.id.sub_price)
    }
}