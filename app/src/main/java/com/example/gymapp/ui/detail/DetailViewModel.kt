package com.example.gymapp.ui.detail

import android.app.Activity
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.Dao.PaymentDao
import com.example.gymapp.Dao.SubscribeDao
import com.example.gymapp.model.Payment
import com.example.gymapp.model.Subscriber
import com.example.gymapp.model.SubscriberWithPayments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private var subDao : SubscribeDao, private var payDao : PaymentDao, val id : Int) : ViewModel() {

    val _edit = MutableLiveData<Boolean>(false)
    private val edit : LiveData<Boolean> get() = _edit


    private var _history = MutableLiveData<SubscriberWithPayments?>()
    val history: LiveData<SubscriberWithPayments?> get() =_history

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome : LiveData<Boolean>  get() = _navigateToHome


    var subscriber = subDao.get(id)

    fun update(name : String , startDate : String ,endDate : String , price : String){
        if (edit.value == true){
            viewModelScope.launch {
                subscriber.value?.name = name
                subscriber.value?.subDate = startDate
                subscriber.value?.subEndDate = endDate
                subscriber.value?.subPrice = price
                subDao.update(subscriber.value!!)

            }

        }else{
            val newSubscriber : Subscriber = Subscriber(name = name, subDate = startDate, subEndDate = endDate, subPrice = price);

            viewModelScope.launch {
                subDao.insert(newSubscriber)
            }
        }
        _navigateToHome.value = true
    }

    fun delete(){
        viewModelScope.launch {
            subDao.delete(subscriber.value!!)
        }
        _navigateToHome.value = true
    }

    fun pay(){
        val Payment = Payment(subscriberId = subscriber.value!!.subscriberId,
            name = subscriber.value!!.name, subDate = subscriber.value!!.subDate, subPrice = subscriber.value!!.subPrice)
        viewModelScope.launch {
            payDao.insert(Payment)
        }
        _navigateToHome.value = true
    }

    fun history()  {
        viewModelScope.launch(Dispatchers.IO) {
            _history.postValue(subDao.getSubscriberWithPaymentsById(subscriber.value?.subscriberId!!))
        }
    }

    fun onNavigateToHome(){
        _navigateToHome.value = false
    }

}