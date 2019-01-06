package com.example.frederikdeprez.tennistime.data.db

import androidx.room.Dao
import androidx.room.Query
import com.example.frederikdeprez.tennistime.data.Tennisclub
import io.reactivex.Single

/**
 * DAO used to manage the CRUD operations of [Tennisclub] and implements [BaseDAO] to manage
 * commonly used database operations
 *
 * This interface is injected by dagger in the classes that need it
 *
 * @see [DataModule]
 */
@Dao
interface TennisclubDataDao: BaseDAO<Tennisclub> {

    /**
     * Get all the [Tennisclub] objects saved in the database
     *
     * @return Observable list of all [Tennisclub] objects in the database
     */
    @Query("select * from tennisclubs")
    fun getAllTennisclubs(): Single<List<Tennisclub>>

    /**
     * Get the amount of rows of [Tennisclub] in the database: Used to check whether the database is empty and whether data
     * should be inserted.
     *
     * @return The amount of rows in the [Tennisclub] table
     */
    @Query("select count(*) from tennisclubs")
    fun getRowCount(): Int
}