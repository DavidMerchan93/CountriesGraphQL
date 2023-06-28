package com.david.countriesgraphql.domain.useCase

import com.david.countriesgraphql.domain.CountryClient

class GetCountriesUseCase(
    private val countryClient: CountryClient,
) {
    suspend operator fun invoke() = countryClient
        .getCountries()
        .sortedBy { it.name }
}
