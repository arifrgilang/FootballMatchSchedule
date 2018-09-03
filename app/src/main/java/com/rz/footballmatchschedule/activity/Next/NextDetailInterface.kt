package com.rz.footballmatchschedule.activity.Next

import com.rz.footballmatchschedule.model.Event
import com.rz.footballmatchschedule.model.Team

interface NextDetailInterface {
    fun showLoading()
    fun hideLoading()
    fun setEvent(event: List<Event>)
    fun setHomeBadges(teams: List<Team>)
    fun setAwayBadges(teams: List<Team>)
}