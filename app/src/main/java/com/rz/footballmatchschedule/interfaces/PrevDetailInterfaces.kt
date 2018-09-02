package com.rz.footballmatchschedule.interfaces

import com.rz.footballmatchschedule.model.Event

interface PrevDetailInterfaces {
    fun showLoading()
    fun hideLoading()
    fun setEvent(event: List<Event>)
}