package com.loc.finanstakip.data.model

import com.google.gson.annotations.SerializedName

data class ExpenseDto(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("category") val category: String
) {
    fun toDomain(): Expense {
        return Expense(
            id = this.id ?: "",
            title = this.title,
            amount = this.amount,
            category = this.category
        )
    }
}