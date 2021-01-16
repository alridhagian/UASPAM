package com.pam.sepakbolainformasi.presenter

import com.google.gson.Gson
import com.pam.sepakbolainformasi.view.ListTeamView
import com.pam.sepakbolainformasi.api.ApiRepository
import com.pam.sepakbolainformasi.model.TeamsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamListPresenter (
    private val view: ListTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getListTeam(idLeague: String?) {

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getTeamList(idLeague)).await(),
                TeamsResponse::class.java)

            view.showListTeam(data.teamsResponse)
        }
    }
}