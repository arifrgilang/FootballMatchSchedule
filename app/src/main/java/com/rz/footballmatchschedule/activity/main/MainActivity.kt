package com.rz.footballmatchschedule.activity.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.gson.Gson
import com.rz.footballmatchschedule.R
import com.rz.footballmatchschedule.activity.main.adapter.FavAdapter
import com.rz.footballmatchschedule.activity.main.adapter.NextAdapter
import com.rz.footballmatchschedule.activity.main.adapter.PrevAdapter
import com.rz.footballmatchschedule.api.ApiRepository
import com.rz.footballmatchschedule.utils.invisible
import com.rz.footballmatchschedule.model.Match
import com.rz.footballmatchschedule.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity(), MainInterface {
    private var resMatchList: MutableList<Match> = mutableListOf()

    private lateinit var prevAdapter: PrevAdapter
    private lateinit var nextAdapter: NextAdapter
    private lateinit var favAdapter: FavAdapter
    private lateinit var presenter: MainPresenter

    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var listMatches: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate view
        relativeLayout{
            lparams (width = matchParent, height = matchParent)
            // Recycler View
            swipeLayout = swipeRefreshLayout {
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    listMatches = recyclerView {
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                        id = R.id.list_matches
                    }

                    progressBar = progressBar {
                        id = R.id.progress_bar
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }.lparams(width = matchParent, height = matchParent){
                above(R.id.bottom_bar)
            }
            // Bottom Navigation Bar
            linearLayout{
                id = R.id.bottom_bar
                orientation = LinearLayout.VERTICAL

                view {
                    backgroundColor = R.color.colorAccent
                }.lparams(height = dip(2), width = matchParent)

                bottomNavigationView {
                    id = R.id.bottom_nav_view
                    inflateMenu(R.menu.bottom_bar)
                }.lparams(width = matchParent, height = wrapContent)
                        .setOnNavigationItemSelectedListener { item ->
                            when (item.itemId) {
                                R.id.prev_menu -> {
                                    if (resMatchList.isEmpty() || listMatches.adapter != prevAdapter) {
                                        loadMatches("prev")
                                    }
                                    return@setOnNavigationItemSelectedListener true
                                }
                                R.id.next_menu -> {
                                    if (resMatchList.isEmpty() || listMatches.adapter != nextAdapter) {
                                        loadMatches("next")
                                    }
                                    return@setOnNavigationItemSelectedListener true
                                }
                                R.id.fav_menu -> {
                                    if (resMatchList.isEmpty() || listMatches.adapter != favAdapter) {
                                        loadMatches("fav")
                                    }
                                    return@setOnNavigationItemSelectedListener true
                                }
                            }
                            false
                        }
            }.lparams(width = matchParent, height = wrapContent){
                alignParentBottom()
            }
        }
        //
        swipeLayout.onRefresh {
            var type: String = ""
            when (listMatches.adapter) {
                prevAdapter -> type = "prev"
                nextAdapter -> type = "next"
                favAdapter -> type = "fav"
            }
            presenter.getMatchList(type, getString(R.string.league_id))
        }
        //
        prevAdapter = PrevAdapter(resMatchList)
        nextAdapter = NextAdapter(resMatchList)
        favAdapter = FavAdapter(resMatchList)
        loadMatches("prev")
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Match>) {
        swipeLayout.isRefreshing = false
        // To handle java.lang.IndexOutOfBoundsException: Inconsistency detected
        listMatches.getRecycledViewPool().clear()
        //
        resMatchList.clear()
        resMatchList.addAll(data)
        //
        prevAdapter.notifyDataSetChanged()
        nextAdapter.notifyDataSetChanged()
    }

    fun loadMatches(type: String?){
        // setup Presenter
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        // setup RVAdapter, changing upon bottom bar clicked
        when (type) {
            "prev" -> listMatches.adapter = prevAdapter
            "next" -> listMatches.adapter = nextAdapter
            "fav" -> listMatches.adapter = favAdapter
        }

        //
        presenter.getMatchList(type, getString(R.string.league_id))
    }

}

