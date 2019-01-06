package com.example.frederikdeprez.tennistime.data.network

import com.example.frederikdeprez.tennistime.data.Player
import com.example.frederikdeprez.tennistime.data.Tennisclub
import com.example.frederikdeprez.tennistime.data.network.response.PlayerDTO
import com.example.frederikdeprez.tennistime.data.network.response.TennisclubDTO
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
    fun getAllTennisclubs(): Single<List<TennisclubDTO>>

    /**
     *  Get all [Player]
     */
    @GET("api/player")
    fun getAllPlayers(): Single<List<PlayerDTO>>

    /**
     *  Get all [Player] from specific [Tennisclub]
     */
    @GET("api/tennisclub/{tennisclubId}/player")
    fun getAllPlayersFromTennisclub(@Path("tennisclubId") tennisclubId: String): Single<List<PlayerDTO>>

    /**
     * Get specific [Player] from [Tennisclub]
     */
    @GET("api/tennisclub/{tennisclubId}/player/{playerId}")
    fun getPlayerFromTennisclub(@Path("tennisclubId") tennisclubId: String, @Path("playerId") playerId: String): Single<PlayerDTO>

    /**
     * Register new [Player] in a [Tennisclub]
     */
    @POST("api/tennisclub/{tennisclubId}/player")
    fun registerNewPlayerInTennisclub(@Path("tennisclubId") tennisclubId: String, @Body player: Player): Single<PlayerDTO>

    /**
     * Change properties from specific (id) player in a specific tennisclub
     */
    @PUT("api/tennisclub/{tennisclubId}/player/{playerId}")
    fun changePlayer(@Path("tennisclubId") tennisclubId: String, @Path("playerId") playerId: String, @Body player: Player): Single<PlayerDTO>
}