package com.example.gymapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gymapp.database.SubscribersDatabase
import com.example.gymapp.databinding.FragmentDetailBinding

import com.example.gymapp.model.Subscriber
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = SubscribersDatabase.getInstance(application).subscribeDao

        binding.insertSub.setOnClickListener {

            val subName = binding.subName.text.toString()
            val subscriptionDate = binding.subDate.text.toString()
            val subscriptionEndDate = binding.subEndDate.text.toString()
            val price = binding.subPrice.text.toString()

            val subscriber = Subscriber(name = subName, subDate = subscriptionDate, subEndDate = subscriptionEndDate, subPrice = price)

            GlobalScope.launch {
                dao.insert(subscriber)
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}