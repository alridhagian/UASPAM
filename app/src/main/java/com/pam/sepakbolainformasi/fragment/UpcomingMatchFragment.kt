package com.pam.sepakbolainformasi.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.pam.sepakbolainformasi.R
import com.pam.sepakbolainformasi.activity.DetailMatchActivity
import com.pam.sepakbolainformasi.adapter.UpcomingMatchAdapter
import com.pam.sepakbolainformasi.api.ApiRepository
import com.pam.sepakbolainformasi.presenter.UpcomingPresenter
import com.google.gson.Gson
import com.pam.sepakbolainformasi.model.Match
import com.pam.sepakbolainformasi.view.UpcomingMatchView
import kotlinx.android.synthetic.main.fragment_list_upcomingleague.*
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */
class UpcomingMatchFragment : Fragment(), UpcomingMatchView {

    lateinit var presenter: UpcomingPresenter
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var adapter: UpcomingMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_upcomingleague, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = UpcomingPresenter(this, apiRepository, gson)


        adapter = UpcomingMatchAdapter(context, match) {
            context?.startActivity<DetailMatchActivity>("id" to "${it.idEvent}",
                DetailMatchActivity.ARGS_ID_AWAY to "${it.idAwayTeam}",
                DetailMatchActivity.ARGS_ID_HOME to "${it.idHomeTeam}")
        }

        rvUpcomingMatch.layoutManager = LinearLayoutManager(context)
        rvUpcomingMatch.adapter = adapter
    }

    override fun showUpcomingMatch(data: List<Match>) {
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
