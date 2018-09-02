package com.rz.footballmatchschedule.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Match(
        @SerializedName("idEvent") var eventId: String? = null,
        @SerializedName("dateEvent") var eventDate: String? = null,

        @SerializedName("strHomeTeam") var homeTeamName: String? = null,
        @SerializedName("intHomeScore") var homeScore: String? = null,

        @SerializedName("strAwayTeam") var awayTeamName: String? = null,
        @SerializedName("intAwayScore") var awayScore: String? = null

 ) : Serializable
