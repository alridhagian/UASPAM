package com.pam.sepakbolainformasi.presenter

import com.google.gson.Gson
import com.pam.sepakbolainformasi.view.PreviousMatchView
import com.pam.sepakbolainformasi.api.ApiRepository
import com.pam.sepakbolainformasi.model.MatchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PreviousMatchPresenter (
    private val view: PreviousMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getPreviousMatch(idLeague: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getPreviousMatch(idLeague)).await(),
                MatchResponse::class.java
            )
                view.hideLoading()
                view.showPreviousMatch(data.matchResponse)

        }

    }
}