package com.loc.finanstakip.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.finanstakip.data.model.ExpenseUiState
import com.loc.finanstakip.data.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExpenseUiState>(ExpenseUiState.Loading)
    val uiState: StateFlow<ExpenseUiState> = _uiState.asStateFlow()

    init {
        loadExpenses()
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            _uiState.value = ExpenseUiState.Loading

            repository.fetchExpenses().fold(
                onSuccess = { data ->
                    _uiState.value = ExpenseUiState.Success(data)
                },
                onFailure = { exception ->
                    _uiState.value = ExpenseUiState.Error(exception.message ?: "Bilinmeyen Hata")
                }
            )
        }
    }
}