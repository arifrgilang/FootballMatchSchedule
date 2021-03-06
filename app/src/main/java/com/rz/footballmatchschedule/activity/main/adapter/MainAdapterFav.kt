package com.rz.footballmatchschedule.activity.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rz.footballmatchschedule.R
import com.rz.footballmatchschedule.activity.Fav.FavViewHolder
import com.rz.footballmatchschedule.activity.Prev.PrevDetailActivity
import com.rz.footballmatchschedule.model.Match
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.startActivity

class FavAdapter (private val matches: List<Match>)
    : RecyclerView.Adapter<FavMatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMatchViewHolder {
        return FavMatchViewHolder(FavViewHolder().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: FavMatchViewHolder, position: Int) {
        holder.bindItem(matches[position])
    }
}

class FavMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val teamHomeName: TextView = view.findViewById(R.id.team_home_name)
    private val teamAwayName: TextView = view.findViewById(R.id.team_away_name)

    private val teamHomeScore: TextView = view.findViewById(R.id.team_home_score)
    private val teamAwayScore: TextView = view.findViewById(R.id.team_away_score)

    private val eventDate: TextView = view.findViewById(R.id.match_date)
    fun bindItem(match: Match){
        teamHomeName.text = match.homeTeamName
        teamHomeScore.text = match.homeScore

        teamAwayName.text = match.awayTeamName
        teamAwayScore.text = match.awayScore

        eventDate.text = match.eventDate
        //
        itemView.setOnClickListener {
            itemView.context.startActivity<PrevDetailActivity>(
                    "MATCH_ID" to match.eventId,
                    "HOME_NAME" to match.homeTeamName,
                    "AWAY_NAME" to match.awayTeamName
            )
        }
    }
}