package com.example.gymapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gymapp.database.PaymentsDatabase
import com.example.gymapp.database.SubscribersDatabase
import com.example.gymapp.databinding.FragmentDetailBinding
import com.example.gymapp.model.Payment

import com.example.gymapp.model.Subscriber
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private lateinit var subscriber : Subscriber
    private lateinit var payment : Payment
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val subId = DetailFragmentArgs.fromBundle(requireArguments()).subId

        val application = requireNotNull(this.activity).application
        val subDao = SubscribersDatabase.getInstance(application).subscribeDao
        val paymentDao = PaymentsDatabase.getInstance(application).paymentDao

         var edit = false

        if (subId != -1){
            edit = true
            GlobalScope.launch {
                subscriber = subDao.get(subId)
                payment = paymentDao.get(subId)

                binding.subName.setText(subscriber.name)
                binding.subDate.setText(subscriber.subDate)
                binding.subEndDate.setText(subscriber.subEndDate)
                binding.subPrice.setText(subscriber.subPrice)
            }
        }

        binding.insertSub.setOnClickListener {

            val subName = binding.subName.text.toString()
            val subscriptionDate = binding.subDate.text.toString()
            val subscriptionEndDate = binding.subEndDate.text.toString()
            val price = binding.subPrice.text.toString()


            GlobalScope.launch {
                if (!edit){
                    subscriber = Subscriber(name = subName, subDate = subscriptionDate, subEndDate = subscriptionEndDate, subPrice = price)
                    payment = Payment( name = subName , subDate = subscriptionDate , subPrice = price)
                    subDao.insert(subscriber)
                    paymentDao.insert(payment)
                }else{
                    subscriber.name = subName
                    subscriber.subDate = subscriptionDate
                    subscriber.subEndDate = subscriptionEndDate
                    subscriber.subPrice = price

                    payment.name = subName
                    payment.subDate = subscriptionDate
                    payment.subPrice = price

                    subDao.update(subscriber)
                    paymentDao.update(payment)
                }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}