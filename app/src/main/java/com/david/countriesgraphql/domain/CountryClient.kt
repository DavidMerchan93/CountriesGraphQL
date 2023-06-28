package com.david.countriesgraphql.domain

import com.david.countriesgraphql.domain.model.CountryDetail
import com.david.countriesgraphql.domain.model.CountryItem

interface CountryClient {
    suspend fun getCountries(): List<CountryItem>
    suspend fun getCountry(code: String): CountryDetail?
}
