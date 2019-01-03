package com.example.frederikdeprez.tennistime.data

data class Tennisclub(
        private val tenniclub_id: Long,
        private val name: String,
        private val location: String,
        private val vtvnumber: String,
        private val players: List<Player>
)