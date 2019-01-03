package com.example.frederikdeprez.tennistime.data

import java.util.*

data class Player(
        private val playerId: Long,
        private val name: String,
        private val email: String,
        private val phonenumber: String,
        private val ranking: String,
        private val vtvnumber: String,
        private val availabilities: List<Date>,
        private val avatar: String
)