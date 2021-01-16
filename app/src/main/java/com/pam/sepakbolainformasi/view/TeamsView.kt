package com.pam.sepakbolainformasi.view

import com.pam.sepakbolainformasi.model.Teams

interface TeamsView {
    fun showTeams(homeTeams: List<Teams>, awayTeams: List<Teams>)
}
