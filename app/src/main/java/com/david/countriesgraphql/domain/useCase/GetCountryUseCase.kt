package com.david.countriesgraphql.domain.useCase

import com.david.countriesgraphql.domain.CountryClient
import com.david.countriesgraphql.domain.model.CountryDetail

class GetCountryUseCase(
    private val countryClient: CountryClient,
) {
    suspend operator fun invoke(code: String): CountryDetail? = countryClient
        .getCountry(code)
}
