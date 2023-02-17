package com.example.gymapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gymapp.Dao.PaymentDao
import com.example.gymapp.model.Payment

@Database(entities = [Payment::class] , version =  1 , exportSchema = false)
abstract class PaymentsDatabase : RoomDatabase() {

    abstract val paymentDao : PaymentDao

    companion object{
        @Volatile
        private var INSTANCE: PaymentsDatabase? = null

        fun getInstance(context: Context):PaymentsDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PaymentsDatabase::class.java,
                        "payment_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}