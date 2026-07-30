package com.ivy.settings

import com.ivy.base.legacy.Theme

data class SettingsState(
    val currencyCode: String,
    val name: String,
    val currentTheme: Theme,
    val lockApp: Boolean,
    val showNotifications: Boolean,
    val showHelpfulTips: Boolean,
    val showBudgetWarnings: Boolean,
    val showPlannedPaymentReminders: Boolean,
    val showDailyTransactionReminders: Boolean,
    val showMonthlyReviewReminders: Boolean,
    val showBackupReminders: Boolean,
    val hideCurrentBalance: Boolean,
    val hideIncome: Boolean,
    val treatTransfersAsIncomeExpense: Boolean,
    val startDateOfMonth: String,
    val progressState: Boolean,
    val languageOptionVisible: Boolean
)
