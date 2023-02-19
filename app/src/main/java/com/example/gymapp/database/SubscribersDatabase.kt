package com.example.gymapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gymapp.Dao.PaymentDao
import com.example.gymapp.Dao.SubscribeDao
import com.example.gymapp.model.Payment
import com.example.gymapp.model.Subscriber

@Database(entities = [Subscriber::class , Payment::class] , version = 3 , exportSchema = false)
abstract class SubscribersDatabase : RoomDatabase(){

    abstract val subscribeDao : SubscribeDao
    abstract val paymentDao : PaymentDao

    companion object{
        @Volatile
        private var INSTANCE: SubscribersDatabase? = null

        fun getInstance(context: Context):SubscribersDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscribersDatabase::class.java,
                        "gym_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}