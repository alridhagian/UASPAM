package com.pam.sepakbolainformasi.view

import com.pam.sepakbolainformasi.model.Match

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun getMatch(data: List<Match>)
}