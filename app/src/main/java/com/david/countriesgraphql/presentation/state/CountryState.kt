package com.david.countriesgraphql.presentation.state

import com.david.countriesgraphql.domain.model.CountryDetail

data class CountryState(
    val isLoading: Boolean = false,
    val country: CountryDetail? = null,
    val isError: Boolean = false,
)
