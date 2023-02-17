package com.example.gymapp.ui.home

import androidx.lifecycle.ViewModel
import com.example.gymapp.Dao.SubscribeDao

class HomeViewModel(val dao: SubscribeDao) : ViewModel() {

    var sublists = dao.getAll()
}