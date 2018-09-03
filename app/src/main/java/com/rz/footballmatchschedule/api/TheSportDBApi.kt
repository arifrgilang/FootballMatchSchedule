package com.rz.footballmatchschedule.api

import android.net.Uri
import com.rz.footballmatchschedule.BuildConfig

object TheSportDBApi {
    private const val PASTLEAGUE: String = "eventspastleague.php?id="
    private const val NEXTLEAGUE: String = "eventsnextleague.php?id="
    private const val SEARCHEVENTS: String = "lookupevent.php?id="
    private const val SEARCHTEAMS: String = "searchteams.php?t="

    private lateinit var apiType: String
    fun getMatchesResponse(type: String?, leagueId: String?): String {
        if (type == "prev") {
            apiType = PASTLEAGUE
        }
        else if (type == "next"){
            apiType = NEXTLEAGUE
        }
        return BuildConfig.BASE_URL + apiType + leagueId
    }

    fun getEventsResponse(eventId: String?): String {
        return BuildConfig.BASE_URL + SEARCHEVENTS + eventId
    }

    fun getTeamsResponse(teamName: String?): String {
        return BuildConfig.BASE_URL + SEARCHTEAMS + teamName
    }
}