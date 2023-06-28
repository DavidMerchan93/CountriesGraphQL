package com.david.countriesgraphql.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.david.countriesgraphql.domain.model.CountryDetail
import com.david.countriesgraphql.presentation.viewModel.CountryViewModel

@Composable
fun CountryDetail(
    countryCode: String,
    countryViewModel: CountryViewModel = hiltViewModel(),
) {
    val countryState = countryViewModel.countryState

    countryViewModel.getCountry(countryCode)

    when {
        countryState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(40.dp),
                )
            }
        }

        countryState.country != null -> {
            CountryDetailModal(countryState.country)
        }

        countryState.isError -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Error al cargar los paises.")
            }
        }
    }
}

@Composable
private fun CountryDetailModal(country: CountryDetail) {
}
