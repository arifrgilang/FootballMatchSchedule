package com.rz.footballmatchschedule.activity.main

import com.rz.footballmatchschedule.model.Match

interface MainInterface {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Match>)
}