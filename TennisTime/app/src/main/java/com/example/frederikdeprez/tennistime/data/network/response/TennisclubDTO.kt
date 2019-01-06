package com.example.frederikdeprez.tennistime.data.network.response

import com.example.frederikdeprez.tennistime.data.Tennisclub
import com.google.gson.annotations.SerializedName

/**
 * This class is used to convert a [TennisclubDTO] coming from the backend server to a [Tennisclub].
 * The item is then saved in the [AppDatabase]
 *
 * @param tennisclubId defines mockapi's id used to get players with that id
 * @param name The name of the tennisclub
 * @param location The location of the tennisclub
 * @param vtvnumber The vtvnumber of the tennisclub
 *
 * @constructor Creates a new [TennisclubDTO] with the given params
 */
data class TennisclubDTO(
        @SerializedName("tennisclubId") val tennisclubId: String,
        @SerializedName("name") val name: String,
        @SerializedName("location") val location: String,
        @SerializedName("vtvnumber") val vtvnumber: String
) {

    companion object {
        /**
         * Converts a [TennisclubDTO] object to a [Tennisclub] object and used to convert the response of the
         * request to the backend to models used in the application
         *
         * @param dto The [TennisclubDTO] object that should be converted to a [Tennisclub]
         * @return the [Tennisclub] of the given [TennisclubDTO]
         */
        fun toTennisclub(dto: TennisclubDTO): Tennisclub {
            val tennisclub = Tennisclub(dto.tennisclubId, dto.name, dto.location, dto.vtvnumber)
            return tennisclub
        }
    }


}