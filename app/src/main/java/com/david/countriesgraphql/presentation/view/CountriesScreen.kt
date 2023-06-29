package com.david.countriesgraphql.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.david.countriesgraphql.domain.model.CountryItem
import com.david.countriesgraphql.presentation.viewModel.CountriesViewModel

@Composable
fun CountriesScreen(
    modifier: Modifier = Modifier,
    countriesViewModel: CountriesViewModel = hiltViewModel(),
    onSelectCountry: (code: String) -> Unit,
) {
    val countriesState = countriesViewModel.countriesState

    when {
        countriesState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(40.dp),
                )
            }
        }

        countriesState.countries.isNotEmpty() -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                content = {
                    items(countriesState.countries) { item ->
                        CountryItem(
                            Modifier.clickable {
                                onSelectCountry(item.code)
                            },
                            item,
                        )
                    }
                },
            )
        }

        countriesState.isError -> {
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
private fun CountryItem(
    modifier: Modifier,
    countryItem: CountryItem,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = countryItem.emoji,
            fontSize = 28.sp,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = countryItem.name,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = countryItem.code,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
    }
}
