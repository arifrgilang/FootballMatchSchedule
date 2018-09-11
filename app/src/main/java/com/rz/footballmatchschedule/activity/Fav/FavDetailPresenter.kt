package com.rz.footballmatchschedule.activity.Fav

import com.google.gson.Gson
import com.rz.footballmatchschedule.api.ApiRepository
import com.rz.footballmatchschedule.api.TheSportDBApi
import com.rz.footballmatchschedule.model.EventResponse
import com.rz.footballmatchschedule.model.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FavDetailPresenter(private val view: FavDetailInterface,
                         private val apiRepo: ApiRepository,
                         private val gson: Gson) {
    fun getEventList(eventId: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepo
                    .doRequest(TheSportDBApi.getEventsResponse(eventId)),
                    EventResponse::class.java
            )

            uiThread {
                println("Response fav=> ${data.events}")
                view.setEvent(data.events)
                view.hideLoading()
            }
        }
    }

    fun getBadges(type: String, teamName: String?){
        view.showLoading()
        doAsync {
            val badgeUrl = gson.fromJson(apiRepo
                    .doRequest(TheSportDBApi.getTeamsResponse(teamName)),
                    TeamResponse::class.java
            )

            uiThread {
                println("Response => ${badgeUrl}")
                if (type == "home"){
                    view.setHomeBadges(badgeUrl.teams)
                } else if (type == "away"){
                    view.setAwayBadges(badgeUrl.teams)
                }
                view.hideLoading()
            }
        }
    }
}