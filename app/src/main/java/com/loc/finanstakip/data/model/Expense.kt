package com.loc.finanstakip.data.model


data class ExpenseDto(
    val id: String?,
    val amount: Double?,
    val category: String?
) {
    fun toDomain(): Expense {
        return Expense(
            id = id ?: "",
            amount = amount ?: 0.0,
            category = category ?: "Bilinmiyor"
        )
    }
}

data class Expense(
    val id: String,
    val amount: Double,
    val category: String
)

sealed class ExpenseUiState {
    object Loading : ExpenseUiState()
    data class Success(val data: List<Expense>) : ExpenseUiState()
    data class Error(val message: String) : ExpenseUiState()
}