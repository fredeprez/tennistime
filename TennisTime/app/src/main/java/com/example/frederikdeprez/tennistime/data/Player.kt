package com.example.frederikdeprez.tennistime.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

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
@Entity(tableName = "players")
data class Player(
        val playerId: String,
        val tennisclubId: String,
        val name: String,
        val email: String,
        val phonenumber: String,
        val ranking: String,
        val vtvnumber: String,
        val avatar: String
) {

    @PrimaryKey(autoGenerate = true)
    var playerDatabaseId: Int = 0

    companion object {
        /**
         * Converts a [Player] object to a [PlayerDTO] object and is used to send data back to the
         * backend server
         *
         * @param player The [Player] that needs to be converted to a [PlayerDTO]
         * @return the [PlayerDTO] of the given [Player]
         */
        fun toPlayerDTO(player: Player): PlayerDTO {
            val dto = PlayerDTO(player.playerId, player.tennisclubId, player.name, player.email, player.phonenumber, player.ranking, player.vtvnumber, player.avatar)
            return dto
        }
    }
}