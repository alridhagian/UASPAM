package com.pam.sepakbolainformasi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pam.sepakbolainformasi.R
import com.pam.sepakbolainformasi.adapter.ResultSearchAdapter
import com.pam.sepakbolainformasi.api.ApiRepository
import com.pam.sepakbolainformasi.presenter.SearchMatchPresenter
import com.pam.sepakbolainformasi.utils.invisible
import com.pam.sepakbolainformasi.utils.visible
import com.pam.sepakbolainformasi.view.SearchMatchView
import com.google.gson.Gson
import com.pam.sepakbolainformasi.model.Match
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : AppCompatActivity(), SearchMatchView {

    lateinit var searchPresenter: SearchMatchPresenter
    private lateinit var adapter: ResultSearchAdapter
    private var match: MutableList<Match> = mutableListOf()

    companion object {
        const val ARGS_QUERY = "ARGS_QUERY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val query = intent.getStringExtra(ARGS_QUERY)
        val actionBar = supportActionBar
        actionBar?.title = "Result for : $query"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val apiRepository = ApiRepository()
        val gson = Gson()
        searchPresenter = SearchMatchPresenter(this, apiRepository, gson)
        searchPresenter.getSearchMatch(query)

        adapter = ResultSearchAdapter(this, match)
        rvSearchMatch.layoutManager = LinearLayoutManager(this)
        rvSearchMatch.adapter = adapter
    }

    override fun showLoading() {
        progressBarSearch.visible()
    }

    override fun hideLoading() {
        progressBarSearch.invisible()
    }

    override fun getMatch(data: List<Match>) {
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
