package com.rz.footballmatchschedule.view

import android.graphics.Typeface
import android.text.Layout
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.rz.footballmatchschedule.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class PrevMatchItemView : AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            cardView{
                lparams(width = matchParent, height = wrapContent){
                    margin = dip(6)
                }
                linearLayout{
                    padding = dip(8)
                    orientation = LinearLayout.VERTICAL
                    // Match Date
                    textView{
                        id = R.id.match_date
                        textColor = R.color.colorAccent
                    }.lparams{
                        height = wrapContent
                        width = wrapContent
                        gravity = Gravity.CENTER
                        bottomMargin = dip(6)
                    }
                    // Team A vs Team B score
                    linearLayout{
                        orientation = LinearLayout.HORIZONTAL
                        // Team A Name
                        textView{
                            id = R.id.team_home_name
                            textSize = 18f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams{
                            height = wrapContent
                            width = dip(0)
                            weight = 5f
                        }
                        // Team A Score
                        textView{
                            id = R.id.team_home_score
                            textSize = 20f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams{
                            height = wrapContent
                            width = dip(0)
                            weight = 1f
                        }
                        // vs
                        textView{
                            id = R.id.vs
                            textSize = 16f
                            text = "vs"
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams{
                            height = wrapContent
                            width = dip(0)
                            weight = 1f
                        }
                        // Team B Score
                        textView{
                            id = R.id.team_away_score
                            textSize = 20f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams{
                            height = wrapContent
                            width = dip(0)
                            weight = 1f
                        }
                        // Team B Name
                        textView{
                            id = R.id.team_away_name
                            textSize = 18f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams{
                            height = wrapContent
                            width = dip(0)
                            weight = 5f
                        }
                    }.lparams(width = matchParent, height = wrapContent)
                }.lparams(width = matchParent, height = wrapContent)
            }
        }
    }
}