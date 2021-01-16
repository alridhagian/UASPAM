package com.pam.sepakbolainformasi.view

import com.pam.sepakbolainformasi.model.Match


interface UpcomingMatchView {
    fun showUpcomingMatch(data: List<Match>)
}