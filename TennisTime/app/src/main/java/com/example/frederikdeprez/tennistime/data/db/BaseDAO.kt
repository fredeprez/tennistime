package com.example.frederikdeprez.tennistime.data.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Source: https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1
 *
 * [BaseDAO] defines commonly used database queries that are shared between [PlayerDataDAO] and [TennisclubDataDAO]
 *
 * @param T T is the entity type of the item stored in the [AppDatabase] such as [Player] or [Tennisclub]
 */
interface BaseDAO<T> {

    /**
     * Create a new record in the database
     * @param item The item that should be inserted in the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createOne(item: T)

    /**
     * Insert a list of items in the database
     * @param items The list of items that should be inserted in the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMany(items: List<T>)
}