package com.pam.sepakbolainformasi.view

import com.pam.sepakbolainformasi.model.Teams

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun getSearchTeam(data: List<Teams>)
}