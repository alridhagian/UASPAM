package com.pam.sepakbolainformasi.presenter

import com.google.gson.Gson
import com.pam.sepakbolainformasi.api.ApiRepository
import com.pam.sepakbolainformasi.model.DetailMatchResponse
import com.pam.sepakbolainformasi.view.DetailMatchView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailMatchPresenter (
    private val view: DetailMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getDetailMatch(idEvents: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getMatchDetails(idEvents)).await(),
                DetailMatchResponse::class.java
            )

            view.hideLoading()
            view.showDetailMatch(data.detailMatchResponse)
        }

    }
}