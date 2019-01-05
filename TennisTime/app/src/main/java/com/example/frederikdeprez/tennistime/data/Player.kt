package com.example.frederikdeprez.tennistime.data

import java.util.*

data class Player(
        val playerId: Long,
        val tennisclubId: Long,
        val name: String,
        val email: String,
        val phonenumber: String,
        val ranking: String,
        val vtvnumber: String,
        val availabilities: List<Date>,
        val avatar: String
)