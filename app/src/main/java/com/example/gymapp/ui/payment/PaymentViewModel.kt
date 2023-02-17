package com.example.gymapp.ui.payment

import androidx.lifecycle.ViewModel
import com.example.gymapp.Dao.PaymentDao

class PaymentViewModel(val dao : PaymentDao) : ViewModel() {

    val payments = dao.getAll()
}