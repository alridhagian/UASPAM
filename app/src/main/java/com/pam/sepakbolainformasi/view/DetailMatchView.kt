package com.pam.sepakbolainformasi.view

import com.pam.sepakbolainformasi.model.DetailMatch

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(data: List<DetailMatch?>?)
}