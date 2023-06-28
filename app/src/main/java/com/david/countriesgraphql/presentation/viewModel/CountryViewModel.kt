package com.david.countriesgraphql.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.countriesgraphql.domain.useCase.GetCountryUseCase
import com.david.countriesgraphql.presentation.state.CountryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase,
) : ViewModel() {

    var countryState by mutableStateOf(CountryState(isLoading = true))
        private set

    fun getCountry(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val country = getCountryUseCase(code)
            countryState = if (country != null) {
                CountryState(country = country)
            } else {
                CountryState(isError = true)
            }
        }
    }
}
