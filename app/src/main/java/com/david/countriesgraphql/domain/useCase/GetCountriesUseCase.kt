package com.david.countriesgraphql.domain.useCase

import com.david.countriesgraphql.domain.CountryClient
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val countryClient: CountryClient,
) {
    suspend operator fun invoke() = countryClient
        .getCountries()
        .sortedBy { it.name }
}
