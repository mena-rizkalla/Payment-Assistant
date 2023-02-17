package com.example.gymapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.Dao.SubscribeDao
import com.example.gymapp.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class HomeFactory(private val dao: SubscribeDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            return HomeViewModel(dao = dao) as T

        throw IllegalArgumentException("Unknown ViewModel")
    }
}