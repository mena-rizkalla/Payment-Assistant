package com.example.gymapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.databinding.SubItemBinding
import com.example.gymapp.model.Subscriber
import com.example.gymapp.ui.home.HomeFragmentDirections

class SubsAdapter(private val context: Context, private val subList: List<Subscriber>) : RecyclerView.Adapter<SubsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SubItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val subscriber = subList[position]
        holder.name.text = subscriber.name
        holder.endDate.text = subscriber.subEndDate
        holder.date.text = subscriber.subDate
        holder.price.text = subscriber.subPrice

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragment2ToDetailFragment(subscriber.subscriberId)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return subList.size
    }

     class ViewHolder(private val binding: SubItemBinding) : RecyclerView.ViewHolder(binding.root){
         val name = binding.name
         val endDate = binding.endDate
         val date = binding.date
         val price = binding.price
    }

}