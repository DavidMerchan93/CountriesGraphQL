package com.david.countriesgraphql.domain.useCase

import com.david.countriesgraphql.domain.CountryClient
import com.david.countriesgraphql.domain.model.CountryDetail
import javax.inject.Inject

class GetCountryUseCase @Inject constructor(
    private val countryClient: CountryClient,
) {
    suspend operator fun invoke(code: String): CountryDetail? = countryClient
        .getCountry(code)
}
