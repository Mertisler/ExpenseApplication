package com.loc.finanstakip.data.repository

import com.loc.finanstakip.data.model.Expense

interface ExpenseRepository{
        suspend fun fetchExpenses(): Result<List<Expense>>
    }


