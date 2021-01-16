package com.pam.sepakbolainformasi.presenter

import com.google.gson.Gson
import com.pam.sepakbolainformasi.api.ApiRepository
import com.pam.sepakbolainformasi.model.DetailTeamResponse
import com.pam.sepakbolainformasi.view.DetailTeamView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailTeamPresenter(
    private val view: DetailTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getTeamDetail(idTeam: String?) {

        GlobalScope.launch (Dispatchers.Main) {

            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getTeamDetails(idTeam)).await(),
                DetailTeamResponse::class.java)

            view.showTeamDetail(data.detailTeamsResponse)
        }
    }
}
