package com.example.gymapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gymapp.model.Subscriber

@Dao
interface SubscribeDao {

     @Insert
    suspend fun insert(subscriber: Subscriber)

    @Update
    suspend fun update(subscriber: Subscriber)

    @Delete
    suspend fun delete(subscriber: Subscriber)


    @Query("SELECT * FROM gym_table WHERE subscriberId = :id")
    fun get(id : Int) : LiveData<Subscriber>

    @Query("SELECT * FROM gym_table")
    fun getAll() : LiveData<List<Subscriber>>

}