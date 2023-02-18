package com.example.gymapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.gymapp.R
import com.example.gymapp.database.PaymentsDatabase
import com.example.gymapp.database.SubscribersDatabase
import com.example.gymapp.databinding.FragmentDetailBinding
import com.example.gymapp.factory.DetailFactory
import com.example.gymapp.model.Payment
import com.example.gymapp.model.Subscriber


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private lateinit var subscriber : Subscriber
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val subId = DetailFragmentArgs.fromBundle(requireArguments()).subId

        val application = requireNotNull(this.activity).application
        val subDao = SubscribersDatabase.getInstance(application).subscribeDao
        val paymentDao = PaymentsDatabase.getInstance(application).paymentDao

        val detailFactory = DetailFactory(subDao,paymentDao,subId)
        val detailViewModel = ViewModelProvider(this,detailFactory)[DetailViewModel::class.java]


        if (subId != -1){
            binding.insertSub.text = "Update"
            detailViewModel._edit.value = true

            detailViewModel.subscriber.observe(viewLifecycleOwner , Observer {
                subscriber = it
                binding.subName.setText(subscriber.name)
                binding.subDate.setText(subscriber.subDate)
                binding.subEndDate.setText(subscriber.subEndDate)
                binding.subPrice.setText(subscriber.subPrice)
            })

            detailViewModel.payment.observe(viewLifecycleOwner , Observer {

            })
        }

        binding.insertSub.setOnClickListener {

            val subName = binding.subName.text.toString()
            val subscriptionDate = binding.subDate.text.toString()
            val subscriptionEndDate = binding.subEndDate.text.toString()
            val price = binding.subPrice.text.toString()

            detailViewModel.update(subName,subscriptionDate,subscriptionEndDate,price)

            view.findNavController().navigate(R.id.action_detailFragment_to_homeFragment2)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}