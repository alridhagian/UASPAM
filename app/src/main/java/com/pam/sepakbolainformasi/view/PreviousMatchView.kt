package com.pam.sepakbolainformasi.view

import com.pam.sepakbolainformasi.model.Match

interface PreviousMatchView {
    fun showLoading()
    fun hideLoading()
    fun showPreviousMatch(data: List<Match>)
}