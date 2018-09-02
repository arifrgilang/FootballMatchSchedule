package com.rz.footballmatchschedule.interfaces

import com.rz.footballmatchschedule.model.Match

interface MainInterfaces {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Match>)
}