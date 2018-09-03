package com.rz.footballmatchschedule.presenter

import com.google.gson.Gson
import com.rz.footballmatchschedule.api.ApiRepository
import com.rz.footballmatchschedule.api.TheSportDBApi
import com.rz.footballmatchschedule.interfaces.MainInterfaces
import com.rz.footballmatchschedule.interfaces.PrevDetailInterfaces
import com.rz.footballmatchschedule.model.EventList
import com.rz.footballmatchschedule.model.MatchList
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PrevDetailPresenter(private val view: PrevDetailInterfaces,
                          private val apiRepo: ApiRepository,
                          private val gson: Gson) {
    fun getEventList(eventId: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepo
                    .doRequest(TheSportDBApi.getEventsResponse(eventId)),
                    EventList::class.java
            )

            uiThread {
                println("Response => ${data.events}")
                view.setEvent(data.events)
                view.hideLoading()
                //view.showTeamList(data.events)
            }
        }
    }
}