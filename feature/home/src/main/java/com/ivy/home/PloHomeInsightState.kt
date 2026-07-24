package com.ivy.home

import androidx.compose.runtime.Immutable
import com.ivy.base.legacy.TransactionHistoryItem
import com.ivy.legacy.data.BufferInfo
import com.ivy.legacy.data.LegacyDueSection
import com.ivy.wallet.domain.pure.data.IncomeExpensePair
import java.math.BigDecimal
import kotlin.math.abs

@Immutable
data class PloHomeInsightState(
    val hasTransactions: Boolean,
    val overdueCount: Int,
    val upcomingCount: Int,
    val overdueAmount: Double,
    val upcomingAmount: Double,
    val balanceAfterPlanned: Double,
    val bufferExceededAmount: Double,
) {
    val hasPlannedPayments: Boolean = overdueCount > 0 || upcomingCount > 0
    val hasBufferWarning: Boolean = bufferExceededAmount > 0.0
}

fun ploHomeInsightState(
    history: List<TransactionHistoryItem>,
    balance: BigDecimal,
    buffer: BufferInfo,
    upcoming: LegacyDueSection,
    overdue: LegacyDueSection,
): PloHomeInsightState {
    val planned = listOf(upcoming.stats, overdue.stats)
    val plannedIncome = planned.sumOf { it.income }
    val plannedExpenses = planned.sumOf { it.expense.abs() }
    val plannedImpact = plannedIncome - plannedExpenses
    val balanceAfterPlanned = balance + plannedImpact
    val bufferExceeded = if (buffer.amount > BigDecimal.ZERO && balance < buffer.amount) {
        buffer.amount - balance
    } else {
        BigDecimal.ZERO
    }

    return PloHomeInsightState(
        hasTransactions = history.isNotEmpty(),
        overdueCount = overdue.trns.size,
        upcomingCount = upcoming.trns.size,
        overdueAmount = overdue.stats.absoluteFlow(),
        upcomingAmount = upcoming.stats.absoluteFlow(),
        balanceAfterPlanned = balanceAfterPlanned.toDouble(),
        bufferExceededAmount = bufferExceeded.toDouble(),
    )
}

private fun IncomeExpensePair.absoluteFlow(): Double {
    return abs((income - expense.abs()).toDouble())
}
