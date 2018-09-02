package com.rz.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rz.footballmatchschedule.R
import com.rz.footballmatchschedule.model.Match
import com.rz.footballmatchschedule.view.PrevMatchItemView
import org.jetbrains.anko.AnkoContext

class NextAdapter (private val matches: List<Match>)
    : RecyclerView.Adapter<NextMatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextMatchViewHolder {
        return NextMatchViewHolder(PrevMatchItemView().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holderNext: NextMatchViewHolder, position: Int) {
        holderNext.bindItem(matches[position])
    }
}

class NextMatchViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val teamHomeName: TextView = view.findViewById(R.id.team_home_name)
    private val teamAwayName: TextView = view.findViewById(R.id.team_away_name)

    private val eventDate: TextView = view.findViewById(R.id.match_date)
    fun bindItem(match: Match){
        teamHomeName.text = match.homeTeamName
        teamAwayName.text = match.awayTeamName

        eventDate.text = match.eventDate
    }
}