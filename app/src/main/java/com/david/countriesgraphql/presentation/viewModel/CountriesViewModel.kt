package com.david.countriesgraphql.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.countriesgraphql.domain.useCase.GetCountriesUseCase
import com.david.countriesgraphql.presentation.state.CountriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
) : ViewModel() {

    var countriesState by mutableStateOf(CountriesState(isLoading = true))
        private set

    init {
        getCountries()
    }

    private fun getCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val countries = getCountriesUseCase()
            if (countries.isNotEmpty()) {
                countriesState = CountriesState(countries = countries)
            } else {
                countriesState = CountriesState(isError = true)
            }
        }
    }
}
