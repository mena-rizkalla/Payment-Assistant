package com.example.gymapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gymapp.model.Payment

@Dao
interface PaymentDao {

    @Insert
    suspend fun insert(payment: Payment)

    @Update
    suspend fun update(payment: Payment)

    @Delete
    suspend fun delete(payment: Payment)

    @Query("SELECT * FROM payment_database WHERE subscriberId = :id")
    fun get(id : Int) : Payment

    @Query("SELECT * FROM payment_database")
     fun getAll() : LiveData<List<Payment>>
}