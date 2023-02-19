package com.example.gymapp.ui.detail

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.R
import com.example.gymapp.adapter.PaymentAdapter
import com.example.gymapp.database.SubscribersDatabase
import com.example.gymapp.databinding.FragmentDetailBinding
import com.example.gymapp.factory.DetailFactory
import com.example.gymapp.model.Payment
import com.example.gymapp.model.Subscriber


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private lateinit var subscriber : Subscriber
    private lateinit var payment: Payment
    private val binding get() = _binding!!

    private lateinit var dialog: AlertDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val subId = DetailFragmentArgs.fromBundle(requireArguments()).subId

        val application = requireNotNull(this.activity).application
        val subDao = SubscribersDatabase.getInstance(application).subscribeDao
        val paymentDao = SubscribersDatabase.getInstance(application).paymentDao

        val detailFactory = DetailFactory(subDao,paymentDao,subId)
        val detailViewModel = ViewModelProvider(this,detailFactory)[DetailViewModel::class.java]

        binding.deletebutton.visibility = View.GONE
        binding.paybtn.visibility = View.GONE
        binding.historybtn.visibility = View.GONE

        if (subId != -1){
            binding.deletebutton.visibility = View.VISIBLE
            binding.paybtn.visibility = View.VISIBLE
            binding.historybtn.visibility = View.VISIBLE
            binding.insertSub.text = "Update"
            detailViewModel._edit.value = true

            detailViewModel.subscriber.observe(viewLifecycleOwner , Observer {
                subscriber = it
                binding.subName.setText(subscriber.name)
                binding.subDate.setText(subscriber.subDate)
                binding.subEndDate.setText(subscriber.subEndDate)
                binding.subPrice.setText(subscriber.subPrice)
            })

           /** detailViewModel.payment.observe(viewLifecycleOwner , Observer {
               // payment = it
            })**/
        }

        binding.insertSub.setOnClickListener {

            val subName = binding.subName.text.toString()
            val subscriptionDate = binding.subDate.text.toString()
            val subscriptionEndDate = binding.subEndDate.text.toString()
            val price = binding.subPrice.text.toString()

            detailViewModel.update(subName,subscriptionDate,subscriptionEndDate,price)

            view.findNavController().navigate(R.id.action_detailFragment_to_homeFragment2)
        }

        binding.deletebutton.setOnClickListener {
            detailViewModel.delete()
            view.findNavController().navigate(R.id.action_detailFragment_to_homeFragment2)
        }

        binding.paybtn.setOnClickListener {
            detailViewModel.pay()
            view.findNavController().navigate(R.id.action_detailFragment_to_homeFragment2)
        }

        binding.historybtn.setOnClickListener {

            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.user_payment_dialog, null)
            val builder = AlertDialog.Builder(requireActivity(), R.style.MyDialogTheme)
            builder.setCancelable(true)
            builder.setTitle("PAYMENT HISTORY")
            val recycler = dialogView.findViewById<RecyclerView>(R.id.recycler_user_payments)
            recycler.layoutManager = LinearLayoutManager(activity)
            detailViewModel.history()

            detailViewModel.history.observe(viewLifecycleOwner , Observer {
                val adapter = it?.let { it1 -> PaymentAdapter(application, it1.payments) }
                recycler.adapter = adapter
            })

            builder.setView(dialogView)
            dialog = builder.create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }

        return view
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}