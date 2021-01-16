package com.pam.sepakbolainformasi.presenter


import com.google.gson.Gson
import com.pam.sepakbolainformasi.api.ApiRepository
import com.pam.sepakbolainformasi.model.MatchResponse
import com.pam.sepakbolainformasi.view.SearchMatchView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter (
    private val view: SearchMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getSearchMatch(teamsName: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getSearchMatch(teamsName)).await(),
                MatchResponse::class.java
            )

                view.hideLoading()
                view.getMatch(data.searchMatchResponse.filter { it.strSport.equals("Soccer")})

        }

    }
}