package com.david.countriesgraphql.data

import com.apollographql.apollo3.ApolloClient
import com.david.CountriesQuery
import com.david.CountryQuery
import com.david.countriesgraphql.data.mapper.toCountryDetail
import com.david.countriesgraphql.data.mapper.toCountryItem
import com.david.countriesgraphql.domain.CountryClient
import com.david.countriesgraphql.domain.model.CountryDetail
import com.david.countriesgraphql.domain.model.CountryItem

class ApolloCountryClient(
    private val apolloClient: ApolloClient,
) : CountryClient {
    override suspend fun getCountries(): List<CountryItem> {
        return apolloClient.query(CountriesQuery())
            .execute()
            .data?.countries
            ?.map {
                it.toCountryItem()
            } ?: emptyList()
    }

    override suspend fun getCountry(code: String): CountryDetail? {
        return apolloClient.query(CountryQuery(code))
            .execute()
            .data?.country?.toCountryDetail()
    }
}
