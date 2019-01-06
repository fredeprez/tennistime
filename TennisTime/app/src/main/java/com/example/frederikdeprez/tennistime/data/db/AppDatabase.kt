package com.example.frederikdeprez.tennistime.data.db

import androidx.room.Database
import com.example.frederikdeprez.tennistime.data.Player
import com.example.frederikdeprez.tennistime.data.Tennisclub

/**
 * The database used to store [Expense] and [Category] coming from the backend server
 * This database uses [RoomDatabase] and is injected by Dagger
 * @see [DataModule]
 */
@Database(
        entities = [Player::class, Tennisclub::class],
        version = 1,
        exportSchema = false
)

abstract class AppDatabase {


}