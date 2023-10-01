package com.application.restaurants.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.application.restaurants.utils.Constants.DATABASE_NAME

@Database(
    entities = [LocalRestaurant::class],
    version = 3,
    exportSchema = false
)
abstract class RestaurantsDb : RoomDatabase() {

    abstract val dao: RestaurantsDao

    companion object {

        @Volatile
        private var INSTANCE: RestaurantsDao? = null

        fun getDaoInstance(context: Context): RestaurantsDao {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = buildDatabase(context).dao
                    INSTANCE = instance
                }
                return instance
            }
        }

        private fun buildDatabase(context: Context): RestaurantsDb {
            return Room.databaseBuilder(
                context.applicationContext,
                RestaurantsDb::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
        }

    }

}