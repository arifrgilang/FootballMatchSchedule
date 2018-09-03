package com.rz.footballmatchschedule.activity.main

import com.google.gson.Gson
import com.rz.footballmatchschedule.model.MatchResponse
import com.rz.footballmatchschedule.api.ApiRepository
import com.rz.footballmatchschedule.api.TheSportDBApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter (private val view: MainInterfaces,
                     private val apiRepo: ApiRepository,
                     private val gson: Gson) {
    fun getMatchList(type: String?, match: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepo
                    .doRequest(TheSportDBApi.getMatchesResponse(type, match)),
                    MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                println("Response => ${data.events}")
                view.showTeamList(data.events)
            }
        }
    }
}