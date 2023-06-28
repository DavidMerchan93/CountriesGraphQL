package com.david.countriesgraphql.presentation.state

import com.david.countriesgraphql.domain.model.CountryItem

data class CountriesState(
    val isLoading: Boolean = false,
    val countries: List<CountryItem> = emptyList(),
    val isError: Boolean = false,
)
