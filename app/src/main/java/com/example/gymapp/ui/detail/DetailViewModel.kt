package com.example.gymapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.Dao.PaymentDao
import com.example.gymapp.Dao.SubscribeDao
import com.example.gymapp.model.Payment
import com.example.gymapp.model.Subscriber
import kotlinx.coroutines.launch

class DetailViewModel(private var subDao : SubscribeDao, private var payDao : PaymentDao, val id : Int) : ViewModel() {

    val _edit = MutableLiveData<Boolean>(false)
    private val edit : LiveData<Boolean> get() = _edit

    var subscriber = subDao.get(id)
    var payment = payDao.get(id)


    fun update(name : String , startDate : String ,endDate : String , price : String){
        if (edit.value == true){
            viewModelScope.launch {
                subscriber.value?.name = name
                subscriber.value?.subDate = startDate
                subscriber.value?.subEndDate = endDate
                subscriber.value?.subPrice = price
                subDao.update(subscriber.value!!)

            }
            viewModelScope.launch {
                payment.value?.name = name
                payment.value?.subDate = startDate
                payment.value?.subPrice = price

                payDao.update(payment.value!!)
            }
        }else{
            val newSubscriber = Subscriber(
                name = name,
                subDate = startDate,
                subEndDate = endDate,
                subPrice = price
            )
            val newPayment = Payment(name = name, subDate = startDate, subPrice = price)
            viewModelScope.launch {
                subDao.insert(newSubscriber)
            }

            viewModelScope.launch {

                payDao.insert(newPayment)
            }
        }
    }

}