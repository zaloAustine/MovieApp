package com.zalo.movieapp.presentation.detail

import com.zalo.movieapp.domain.model.Cast

data class DetailScreenState(
    val isLoading:Boolean = true,
    val cast:List<Cast> = emptyList(),
    val error:String = ""
    )