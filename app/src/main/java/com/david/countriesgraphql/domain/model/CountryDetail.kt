package com.david.countriesgraphql.domain.model

data class CountryDetail(
    val code: String,
    val name: String,
    val emoji: String,
    val languages: List<String>,
    val continent: String,
)
