package com.loc.finanstakip.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loc.finanstakip.data.model.ExpenseUiState
import com.loc.finanstakip.viewmodel.ExpenseViewModel

@Composable
fun ExpenseScreen(
    viewModel: ExpenseViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (state) {
            is ExpenseUiState.Loading -> {
                CircularProgressIndicator()
            }
            is ExpenseUiState.Success -> {
                val expenseList = (state as ExpenseUiState.Success).data

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(expenseList) { expense ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = expense.category, modifier = Modifier.weight(1f))
                            Text(text = "${expense.amount} TL")
                        }
                    }
                }
            }
            is ExpenseUiState.Error -> {
                Text(text = (state as ExpenseUiState.Error).message)
            }
        }
    }
}