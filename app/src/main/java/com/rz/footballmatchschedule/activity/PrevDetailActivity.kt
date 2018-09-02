package com.rz.footballmatchschedule.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.rz.footballmatchschedule.R
import com.rz.footballmatchschedule.api.ApiRepository
import com.rz.footballmatchschedule.interfaces.PrevDetailInterfaces
import com.rz.footballmatchschedule.model.Event
import com.rz.footballmatchschedule.presenter.PrevDetailPresenter
import com.rz.footballmatchschedule.utils.invisible
import com.rz.footballmatchschedule.utils.visible
import org.jetbrains.anko.*

class PrevDetailActivity : AppCompatActivity(), PrevDetailInterfaces{
    private var resEventList: MutableList<Event> = mutableListOf()
    private lateinit var prevPresenter: PrevDetailPresenter

    private lateinit var resEvent: Event
    private lateinit var eventId: String
    private lateinit var score: String
    private lateinit var progressBar: ProgressBar

    private lateinit var matchDate: TextView
    private lateinit var scoreText: TextView

    private lateinit var homeName: TextView
    private lateinit var homeBadges: ImageView
    private lateinit var homeFormation: TextView
    private lateinit var homeScorer: TextView

    private lateinit var awayName: TextView
    private lateinit var awayBadges: ImageView
    private lateinit var awayFormation: TextView
    private lateinit var awayScorer: TextView

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun setEvent(list: List<Event>) {
        resEvent = list[0]
        setView()
    }

    fun setView(){
        matchDate.text = resEvent.dateEvent
        scoreText.text = resEvent.intHomeScore + "-" + resEvent.intAwayScore

        homeName.text = resEvent.strHomeTeam
        homeFormation.text = resEvent.strHomeFormation
        homeScorer.text = resEvent.strHomeGoalDetails
//        homeBadges.src

        awayName.text = resEvent.strAwayTeam
        awayFormation.text = resEvent.strAwayFormation
        awayScorer.text = resEvent.strAwayGoalDetails
//        homeBadges.src
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventId = intent.getStringExtra("MATCH_ID")

        linearLayout{
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            // Scroll view for detail
            scrollView{
                linearLayout{
                    orientation = LinearLayout.VERTICAL

                    progressBar = progressBar {
                    }.lparams{

                    }
                    // separator
                    view{
                        backgroundColor = R.color.colorDarkGrey
                    }.lparams{
                        width = matchParent
                        height = dip(1)
                        topMargin = dip(10)
                        leftMargin = dip(10)
                        rightMargin = dip(10)
                    }
                    // Match Date
                    matchDate = textView{
                        id = R.id.prev_detail_match_date
                    }.lparams{
                        height = wrapContent
                        width = wrapContent
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                    // separator
                    view{
                        backgroundColor = R.color.colorDarkGrey
                    }.lparams{
                        width = matchParent
                        height = dip(1)
                        leftMargin = dip(10)
                        rightMargin = dip(10)
                    }
                    // Home and Away team name
                    linearLayout{
                        orientation = LinearLayout.HORIZONTAL
                        // Home team
                        homeName = textView{
                            id = R.id.prev_detail_home_name
                        }.lparams{
                            width = dip(0)
                            height = wrapContent
                            weight = 1f
                        }
                        // Away team
                        awayName = textView{
                            id = R.id.prev_detail_away_name
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams{
                            width = dip(0)
                            height = wrapContent
                            weight = 1f
                        }
                    }.lparams{
                        width = matchParent
                        height = wrapContent
                        margin = dip(10)
                    }
                    // home away badge
                    linearLayout{
                        orientation = LinearLayout.HORIZONTAL
                        // Home badge
                        homeBadges = imageView{
                            id = R.id.prev_detail_home_badges
                        }.lparams{
                            width = dip(80)
                            height = dip(80)
                            weight = 3f
                        }
                        // score
                        scoreText = textView{
                            id = R.id.prev_detail_score
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textSize = 28f
                        }.lparams{
                            width = wrapContent
                            height = wrapContent
                            weight = 1f
                            gravity = Gravity.CENTER_VERTICAL
                        }
                        // Away badge
                        awayBadges = imageView{
                            id = R.id.prev_detail_home_badges
                        }.lparams{
                            width = dip(80)
                            height = dip(80)
                            weight = 3f
                        }
                    }.lparams{
                        width = matchParent
                        height = wrapContent
                    }
                    // Team formation
                    linearLayout{
                        orientation = LinearLayout.HORIZONTAL
                        // home formation
                        homeFormation = textView{
                            id = R.id.prev_detail_home_formation
                        }.lparams{
                            width = dip(0)
                            height = wrapContent
                            weight = 1f
                        }
                        // away formation
                        awayFormation = textView{
                            id = R.id.prev_detail_away_formation
                        }.lparams{
                            width = dip(0)
                            height = wrapContent
                            weight = 1f
                        }
                    }.lparams{
                        width = matchParent
                        height = wrapContent
                    }
                    // separator
                    view{
                        backgroundColor = R.color.colorDarkGrey
                    }.lparams{
                        width = matchParent
                        height = dip(1)
                        leftMargin = dip(10)
                        rightMargin = dip(10)
                    }
                    // Goals text
                    textView{
                        text = "Goals"
                    }.lparams{
                        width = wrapContent
                        height = wrapContent
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                    // separator
                    view{
                        backgroundColor = R.color.colorDarkGrey
                    }.lparams{
                        width = matchParent
                        height = dip(1)
                        leftMargin = dip(10)
                        rightMargin = dip(10)
                    }
                    // scorer name(s)
                    linearLayout{
                        orientation = LinearLayout.HORIZONTAL
                        // home scorer
                        homeScorer = textView{
                            id = R.id.prev_detail_home_scorer
                        }.lparams{
                            width = dip(0)
                            height = wrapContent
                            weight = 3f
                        }
                        // separator
                        view {
                        }.lparams{
                            width = dip(0)
                            height = wrapContent
                            weight = 2f
                        }
                        // away scorer
                        awayScorer = textView{
                            id = R.id.prev_detail_away_scorer
                        }.lparams{
                            width = dip(0)
                            height = wrapContent
                            weight = 3f
                        }
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                    }
                }.lparams(width = matchParent, height = wrapContent)
            }.lparams(width = matchParent, height = wrapContent)
        }
        init()
    }

    fun init(){
        // setup Presenter
        val request = ApiRepository()
        val gson = Gson()
        prevPresenter = PrevDetailPresenter(this, request, gson)
        prevPresenter.getEventList(eventId)
    }
}
