package com.pam.sepakbolainformasi.view

import com.pam.sepakbolainformasi.model.DetailTeam


interface DetailTeamView{
    fun showTeamDetail(data: List<DetailTeam>)
}