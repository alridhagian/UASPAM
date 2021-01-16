package com.pam.sepakbolainformasi.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.pam.sepakbolainformasi.R
import com.pam.sepakbolainformasi.activity.DetailTeamActivity
import com.pam.sepakbolainformasi.adapter.TeamAdapter
import com.pam.sepakbolainformasi.api.ApiRepository
import com.pam.sepakbolainformasi.presenter.TeamListPresenter
import com.pam.sepakbolainformasi.view.ListTeamView
import com.google.gson.Gson
import com.pam.sepakbolainformasi.model.Teams
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */
class TeamsFragment : Fragment(), ListTeamView {

    private lateinit var presenter: TeamListPresenter
    private lateinit var adapter: TeamAdapter
    private var items: MutableList<Teams> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiRepository = ApiRepository()
        val gson = Gson()

        presenter = TeamListPresenter(this, apiRepository, gson)

        adapter = TeamAdapter(context, items) {
            context?.startActivity<DetailTeamActivity>(DetailTeamActivity.ARGS_ID to "${it.idTeam}")
        }

        rvTeam.layoutManager = LinearLayoutManager(context)
        rvTeam.adapter = adapter
    }

    override fun showListTeam(data: List<Teams>) {
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()
    }


}
