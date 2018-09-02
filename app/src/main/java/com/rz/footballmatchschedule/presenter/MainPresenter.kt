package com.rz.footballmatchschedule.presenter

import com.google.gson.Gson
import com.rz.footballmatchschedule.model.MatchList
import com.rz.footballmatchschedule.api.ApiRepository
import com.rz.footballmatchschedule.api.TheSportDBApi
import com.rz.footballmatchschedule.interfaces.MainInterfaces
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
                    MatchList::class.java
            )

            uiThread {
                view.hideLoading()
                println("Response => ${data.events}")
                view.showTeamList(data.events)
            }
        }
    }
}