package com.example.frederikdeprez.tennistime.data.network.response

import com.example.frederikdeprez.tennistime.data.Player
import com.example.frederikdeprez.tennistime.data.Tennisclub
import com.google.gson.annotations.SerializedName

/**
 * This class is used to convert a [PlayerDTO] coming from the backend server to a [Player].
 * The item is then saved in the [AppDatabase]
 *
 * @param playerId defines mockapi's id used to get players with that id
 * @param tennisclubId defines mockapi's id used to get players belonging to a specific [Tennisclub]
 * @param name The name of the player
 * @param email The email of the player
 * @param phonenumber The phonenumber of the player
 * @param ranking The ranking of the player
 * @param vtvnumber The vtvnumber of the player
 * @param avatar The avatar of the player
 *
 * @constructor Creates a new [Player] with the given params
 */
data class PlayerDTO(
        @SerializedName("playerId") val playerId: String,
        @SerializedName("tennisclubId") val tennisclubId: String,
        @SerializedName("name") val name: String,
        @SerializedName("email") val email: String,
        @SerializedName("phonenumber") val phonenumber: String,
        @SerializedName("ranking") val ranking: String,
        @SerializedName("vtvnumber") val vtvnumber: String,
        @SerializedName("avatar") val avatar: String
) {

    companion object {
        /**
         * Converts a [PlayerDTO] object to a [Player] object and used to convert the response of the
         * request to the backend to models used in the application
         *
         * @param dto The [PlayerDTO] object that should be converted to a [Player]
         * @return the [Player] of the given [PlayerDTO]
         */
        fun toPlayer(dto: PlayerDTO): Player {
            val player = Player(dto.playerId, dto.tennisclubId, dto.name, dto.email, dto.phonenumber, dto.ranking, dto.vtvnumber, dto.avatar)
            return player
        }
    }
}