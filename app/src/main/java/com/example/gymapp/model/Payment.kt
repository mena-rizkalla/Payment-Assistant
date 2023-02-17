package com.example.gymapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payment_database")
data class Payment(
    @PrimaryKey(autoGenerate = true) var subscriberId : Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "subscription_date") var subDate : String,
    @ColumnInfo(name = "subscription_price") var subPrice : String)
