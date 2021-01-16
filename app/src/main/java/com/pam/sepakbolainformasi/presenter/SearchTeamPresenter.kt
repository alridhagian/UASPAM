package com.pam.sepakbolainformasi.presenter

import com.google.gson.Gson
import com.pam.sepakbolainformasi.api.ApiRepository
import com.pam.sepakbolainformasi.model.SearchTeamResponse
import com.pam.sepakbolainformasi.view.SearchTeamView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter (
    private val view: SearchTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getSearchTeam(teamName: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getSearchTeam(teamName)).await(),
                SearchTeamResponse::class.java
            )

            view.hideLoading()
            view.getSearchTeam(data.searchTeamResponse)

        }
    }
}