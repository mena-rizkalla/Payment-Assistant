package com.example.gymapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gym_table")
data class Subscriber(

    @PrimaryKey(autoGenerate = true) var subscriberId : Int = 0,
    @ColumnInfo(name = "name") var name : String = "",
    @ColumnInfo(name = "subscription_date") var subDate : String = "",
    @ColumnInfo(name = "end subscription_data") var subEndDate : String = "",
    @ColumnInfo(name = "subscription_price") var subPrice : String )