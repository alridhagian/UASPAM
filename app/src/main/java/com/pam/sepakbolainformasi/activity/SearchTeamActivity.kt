package com.pam.sepakbolainformasi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pam.sepakbolainformasi.R
import com.pam.sepakbolainformasi.adapter.TeamSearchAdapter
import com.pam.sepakbolainformasi.api.ApiRepository
import com.pam.sepakbolainformasi.presenter.SearchTeamPresenter
import com.pam.sepakbolainformasi.utils.invisible
import com.pam.sepakbolainformasi.utils.visible
import com.pam.sepakbolainformasi.view.SearchTeamView
import com.google.gson.Gson
import com.pam.sepakbolainformasi.model.Teams
import kotlinx.android.synthetic.main.activity_search_team.*

class SearchTeamActivity : AppCompatActivity(), SearchTeamView {


    private lateinit var searchPresenter: SearchTeamPresenter
    private lateinit var adapter: TeamSearchAdapter
    private var teams: MutableList<Teams> = mutableListOf()

    companion object {
        const val ARGS_QUERY = "ARGS_QUERY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        val query = intent.getStringExtra(ARGS_QUERY)
        val actionBar = supportActionBar
        actionBar?.title = "Result for : $query"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val apiRepository = ApiRepository()
        val gson = Gson()

        searchPresenter = SearchTeamPresenter(this, apiRepository, gson)
        searchPresenter.getSearchTeam(query)

        adapter = TeamSearchAdapter(teams, this)
        rvSearchTeam.layoutManager = LinearLayoutManager(this)
        rvSearchTeam.adapter = adapter
    }

    override fun showLoading() {
        progressBarSearchTeam.visible()
    }

    override fun hideLoading() {
        progressBarSearchTeam.invisible()
    }

    override fun getSearchTeam(data: List<Teams>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
