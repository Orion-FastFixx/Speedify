package com.example.speedify.feature_activity.presentation.view_model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.feature_activity.data.datasource.PesananDataSource.getAllPesanan
import com.example.speedify.feature_activity.domain.use_case.PesananUseCase
import com.example.speedify.feature_activity.presentation.PesananState
import com.example.speedify.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PesananViewModel @Inject constructor(
    private val useCases: PesananUseCase
) : ViewModel() {

    private val _pesananState = MutableStateFlow(PesananState())
    val pesananState = _pesananState.asStateFlow()

    init {
        getAllPesanan()
    }

    fun getAllPesanan() {
        viewModelScope.launch {
            useCases.getAllPesanan().asFlow().collect {
                when (it) {
                    is ResultState.Loading -> {
                        _pesananState.value = _pesananState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _pesananState.value = _pesananState.value.copy(
                            isLoading = false,
                            error = null,
                            pesanan = it.data
                        )

                        getPesananProses()

                    }

                    is ResultState.Error -> {
                        _pesananState.value = _pesananState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun getPesananProses() {
        viewModelScope.launch {
            useCases.getPesananProses().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _pesananState.value = _pesananState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _pesananState.value = _pesananState.value.copy(
                            isLoading = false,
                            error = null,
                            proses = it.data
                        )

                        getPesananSelesai()

                    }

                    is ResultState.Error -> {
                        _pesananState.value = _pesananState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun getPesananSelesai() {
        viewModelScope.launch {
            useCases.getPesananSelesai().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _pesananState.value = _pesananState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _pesananState.value = _pesananState.value.copy(
                            isLoading = false,
                            error = null,
                            selesai = it.data
                        )

                        getPesananBatal()

                    }

                    is ResultState.Error -> {
                        _pesananState.value = _pesananState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun getPesananBatal() {
        viewModelScope.launch {
            useCases.getPesananBatal().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _pesananState.value = _pesananState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _pesananState.value = _pesananState.value.copy(
                            isLoading = false,
                            error = null,
                            batal = it.data
                        )

                    }

                    is ResultState.Error -> {
                        _pesananState.value = _pesananState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }


}
