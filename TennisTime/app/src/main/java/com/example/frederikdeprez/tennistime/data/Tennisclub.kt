package com.example.frederikdeprez.tennistime.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This class is used to convert a [TennisclubDTO] coming from the backend server to a [Tennisclub].
 * The item is then saved in the [AppDatabase]
 *
 * @param tennisclubId defines mockapi's id used to get players with that id
 * @param name The name of the tennisclub
 * @param location The location of the tennisclub
 * @param vtvnumber The vtvnumber of the tennisclub
 *
 * @constructor Creates a new tennisclub with the given params
 */
@Entity(tableName = "tennisclubs")
data class Tennisclub(
        val tennisclubId: String,
        val name: String,
        val location: String,
        val vtvnumber: String
) {

    @PrimaryKey(autoGenerate = true)
    var databaseId: Int = 0

    companion object {
        /**
         * Converts a [Tennisclub] object to a [TennisclubDTO] object and is used to send data back to the
         * backend server
         *
         * @param tennisclub The [Tennisclub] that needs to be converted to a [TennisclubDTO]
         * @return the [TennisclubDTO] of the given [Tennisclub]
         */
        fun toTennisclubDTO(tennisclub: Tennisclub): TennisclubDTO {
            val dto = TennisclubDTO(tennisclub.categoryName, tennisclub.categoryDescription, tennisclub.colorCode)
            dto.backendId = tennisclub.backendId
            return dto
        }
    }


}