package com.example.frederikdeprez.tennistime.data.db

import androidx.room.Dao
import androidx.room.Query
import com.example.frederikdeprez.tennistime.data.Player
import io.reactivex.Single

/**
 * DAO used to manage the CRUD operations of [Player] and implements [BaseDAO] to manage
 * commonly used database operations
 *
 * This interface is injected by dagger in the classes that need it
 *
 * @see [DataModule]
 */
@Dao
interface PlayerDataDao {

    /**
     * Get all the [Player] objects saved in the database where the [Player.tennisclubId] =
     * @param tennisclubId
     *
     * @return Observable list of all these [Player] objects in the database
     */
    @Query("select * from players where tennisclubId = :tennisclubId")
    fun getAllPlayersFromTennisclub(tennisclubId: String): Single<List<Player>>

    /**
     * Get the amount of rows of [Player] in the database: Used to check whether the database is empty and whether data
     * should be inserted.
     *
     * @return The amount of rows in the [Player] table
     */
    @Query("select count(*) from players")
    fun getRowCount(): Int
}