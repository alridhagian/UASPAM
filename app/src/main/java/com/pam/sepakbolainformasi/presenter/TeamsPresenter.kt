package com.pam.sepakbolainformasi.presenter

import com.google.gson.Gson
import com.pam.sepakbolainformasi.api.ApiRepository
import com.pam.sepakbolainformasi.model.TeamsResponse
import com.pam.sepakbolainformasi.view.TeamsView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter (
    private val view: TeamsView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getTeams(idHomeTeam: String?, idAwayTeam: String?) {

        GlobalScope.launch(Dispatchers.Main) {
            val homeTeams = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getTeamDetails(idHomeTeam)).await(),
                TeamsResponse::class.java
            )

            val awayTeams = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getTeamDetails(idAwayTeam)).await(),
                TeamsResponse::class.java
            )

                view.showTeams(homeTeams.teamsResponse, awayTeams.teamsResponse)

        }

    }
}


