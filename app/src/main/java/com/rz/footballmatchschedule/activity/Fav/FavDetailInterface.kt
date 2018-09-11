package com.rz.footballmatchschedule.activity.Fav

import com.rz.footballmatchschedule.model.Event
import com.rz.footballmatchschedule.model.Team

interface FavDetailInterface {
    fun showLoading()
    fun hideLoading()
    fun setEvent(event: List<Event>)
    fun setHomeBadges(teams: List<Team>)
    fun setAwayBadges(teams: List<Team>)
}