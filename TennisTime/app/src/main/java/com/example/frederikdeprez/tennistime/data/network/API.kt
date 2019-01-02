package com.example.frederikdeprez.tennistime.data.network

import com.example.frederikdeprez.tennistime.data.Player
import com.example.frederikdeprez.tennistime.data.Tennisclub
import io.reactivex.Single
import retrofit2.http.*

/**
 * [API] provides an interface to retrieve information from the backend server
 * Singleton of [API] is provided by Dagger and instantiated in [DataModule]
 */
interface API {

    // Todo: change some documentation with id

    /**
     * Get all [Tennisclub]
     */
    @GET("api/tennisclub")
    fun getAllTennisclubs(): Single<List<Tennisclub>>

    /**
     *  Get all [Player] from specific [Tennisclub]
     */
    @GET("api/tennisclub/{tennisclub_id}/player")
    fun getAllPlayersFromTennisclub(@Path("tennisclub_id") tennisclub_id: Long): Single<List<Player>>

    /**
     * Get specific [Player] from [Tennisclub]
     */
    @GET("api/tennisclub/{tennisclub_id}/player/{player_id}")
    fun getPlayerFromTennisclub(@Path("tennisclub_id") tennisclub_id: Long, @Path("player_id") player_id: Long): Single<Player>

    /**
     * Register new [Player] in a [Tennisclub]
     */
    @POST("api/tennisclub/{tennisclub_id}/player")
    fun registerNewPlayerInTennisclub(@Path("tennisclub_id") tennisclub_id: Long, @Body player: Player): Single<Player>

    /**
     * Change properties from specific (id) player in a specific tennisclub
     */
    @PUT("api/tennisclub/{tennisclub_id}/player/{player_id}")
    fun changePlayer(@Path("tennisclub_id") tennisclub_id: Long, @Path("player_id") player_id: Long, @Body player: Player): Single<Player>
}