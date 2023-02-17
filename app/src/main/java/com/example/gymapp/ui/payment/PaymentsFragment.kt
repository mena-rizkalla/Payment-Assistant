package com.example.gymapp.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymapp.R
import com.example.gymapp.adapter.PaymentAdapter
import com.example.gymapp.database.PaymentsDatabase
import com.example.gymapp.databinding.FragmentPaymentsBinding
import com.example.gymapp.factory.PaymentFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PaymentsFragment : Fragment() {
    private var _binding : FragmentPaymentsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentsBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val paymentDao = PaymentsDatabase.getInstance(application).paymentDao

        binding.paymentRecyclerView.layoutManager = LinearLayoutManager(application)

        val paymentFactory = PaymentFactory(paymentDao)
        val paymentViewModel = ViewModelProvider(this,paymentFactory).get(PaymentViewModel::class.java)

        paymentViewModel.payments.observe(viewLifecycleOwner , Observer {
            it.let {
                val paymentsAdapter = PaymentAdapter(application,it)
                binding.paymentRecyclerView.adapter = paymentsAdapter
            }
        })


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}