package com.example.frederikdeprez.tennistime.data

import java.util.*

data class Player(
        val playerId: String,
        val tennisclubId: String,
        val name: String,
        val email: String,
        val phonenumber: String,
        val ranking: String,
        val vtvnumber: String,
        val avatar: String
)