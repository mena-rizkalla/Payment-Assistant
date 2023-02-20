package com.example.gymapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gymapp.model.Subscriber
import com.example.gymapp.model.SubscriberWithPayments

@Dao
interface SubscribeDao {

     @Insert
    suspend fun insert(subscriber: Subscriber)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(subscriber: Subscriber)

    @Delete
    suspend fun delete(subscriber: Subscriber)


    @Query("SELECT * FROM gym_table WHERE subscriberId = :id")
    fun get(id : Int) : LiveData<Subscriber>

    @Query("SELECT * FROM gym_table")
    fun getAll() : LiveData<List<Subscriber>>

    @Transaction
    @Query("SELECT * FROM gym_table WHERE subscriberId=:subscriberId")
    fun getSubscriberWithPaymentsById(subscriberId: Int): SubscriberWithPayments?

}