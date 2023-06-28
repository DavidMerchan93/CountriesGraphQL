package com.david.countriesgraphql.data.mapper

import com.david.CountriesQuery
import com.david.CountryQuery
import com.david.countriesgraphql.domain.model.CountryDetail
import com.david.countriesgraphql.domain.model.CountryItem

fun CountriesQuery.Country.toCountryItem(): CountryItem {
    return CountryItem(
        code = code,
        name = name,
        emoji = emoji,
    )
}

fun CountryQuery.Country.toCountryDetail(): CountryDetail {
    return CountryDetail(
        code = code,
        name = name,
        emoji = emoji,
        languages = languages.map { it.name },
        continent = continent.name,
    )
}
