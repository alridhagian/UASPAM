package com.pam.sepakbolainformasi.view

import com.pam.sepakbolainformasi.model.Teams

interface ListTeamView {
    fun showListTeam(data: List<Teams>)
}