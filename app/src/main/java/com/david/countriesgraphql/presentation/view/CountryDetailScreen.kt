package com.david.countriesgraphql.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.david.countriesgraphql.domain.model.CountryDetail
import com.david.countriesgraphql.presentation.viewModel.CountryViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailModal() {
    val codeState = remember { mutableStateOf("CO") }

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true,
    )

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            CountryDetailScreen(codeState.value)
        },
    ) {
        Scaffold { padding ->
            Box(
                modifier = Modifier.padding(padding),
            ) {
                CountriesScreen { code ->
                    codeState.value = code
                    coroutineScope.launch {
                        if (modalSheetState.isVisible) {
                            modalSheetState.hide()
                        } else {
                            modalSheetState.show()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CountryDetailScreen(
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
            CountryDetailModalData(countryState.country)
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CountryDetailModalData(country: CountryDetail) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = country.emoji, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = country.name,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = country.code,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = country.continent,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary,
        )

        Row {
            repeat(country.languages.size) {
                Chip(onClick = {}) {
                    Text(text = country.languages[it], color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}
