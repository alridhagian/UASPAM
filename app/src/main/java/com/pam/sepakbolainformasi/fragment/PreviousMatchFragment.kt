package com.pam.sepakbolainformasi.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.pam.sepakbolainformasi.R
import com.pam.sepakbolainformasi.activity.DetailMatchActivity
import com.pam.sepakbolainformasi.adapter.PreviousMatchAdapter
import com.pam.sepakbolainformasi.api.ApiRepository
import com.pam.sepakbolainformasi.presenter.PreviousMatchPresenter
import com.pam.sepakbolainformasi.utils.invisible
import com.pam.sepakbolainformasi.utils.visible
import com.pam.sepakbolainformasi.view.PreviousMatchView
import com.google.gson.Gson
import com.pam.sepakbolainformasi.model.Match
import kotlinx.android.synthetic.main.fragment_list_previousmatch.*
import org.jetbrains.anko.startActivity

class PreviousMatchFragment : Fragment(), PreviousMatchView {



    lateinit var presenter: PreviousMatchPresenter
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var adapter: PreviousMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_previousmatch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = PreviousMatchPresenter(this, apiRepository, gson)

        adapter = PreviousMatchAdapter(context, match) {
            context?.startActivity<DetailMatchActivity>("id" to "${it.idEvent}",
                DetailMatchActivity.ARGS_ID_AWAY to "${it.idAwayTeam}",
                DetailMatchActivity.ARGS_ID_HOME to "${it.idHomeTeam}")
        }

        rvPreviousMatch.layoutManager = LinearLayoutManager(context)
        rvPreviousMatch.adapter = adapter
    }

    override fun showPreviousMatch(data: List<Match>) {
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressBarPrevious.visible()
    }

    override fun hideLoading() {
        progressBarPrevious.invisible()
    }
}
