package com.example.frederikdeprez.tennistime.data

data class Tennisclub(
        val tenniclubId: Long,
        val name: String,
        val location: String,
        val vtvnumber: String,
        val players: List<Player>
)