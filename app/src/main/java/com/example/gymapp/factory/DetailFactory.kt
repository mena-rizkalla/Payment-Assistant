package com.example.gymapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.Dao.PaymentDao
import com.example.gymapp.Dao.SubscribeDao
import com.example.gymapp.ui.detail.DetailViewModel
import java.lang.IllegalArgumentException

class DetailFactory(private val subDao : SubscribeDao, private val payDao : PaymentDao, private val id : Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java))
            return DetailViewModel(subDao,payDao,id) as T

        throw IllegalArgumentException("Unknown view model")
    }

}