package com.pam.sepakbolainformasi.presenter

import com.google.gson.Gson
import com.pam.sepakbolainformasi.api.ApiRepository
import com.pam.sepakbolainformasi.model.MatchResponse
import com.pam.sepakbolainformasi.view.UpcomingMatchView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpcomingPresenter (
    private val view: UpcomingMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getUpcomingMatch(idLeague: String?) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getUpcomingMatch(idLeague)).await(),
                MatchResponse::class.java
            )

                view.showUpcomingMatch(data.matchResponse)

        }

    }
}

