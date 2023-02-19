package com.example.gymapp.model

import androidx.room.Embedded
import androidx.room.Relation


data class SubscriberWithPayments(

    @Embedded val subscriber: Subscriber,
    @Relation (
        parentColumn = "subscriberId",
        entityColumn = "subscriberId"
            )
    val payments: List<Payment>
)
