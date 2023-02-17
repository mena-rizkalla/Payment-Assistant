package com.example.gymapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.Dao.PaymentDao
import com.example.gymapp.ui.payment.PaymentViewModel

class PaymentFactory(private val dao : PaymentDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PaymentViewModel::class.java))
            return PaymentViewModel(dao) as T
        throw IllegalArgumentException("Unknown viewMode")
    }
}