package com.ivy.settings

import com.ivy.domain.RootScreen

sealed interface SettingsEvent {
    data class SetCurrency(val newCurrency: String) : SettingsEvent
    data class SetName(val newName: String) : SettingsEvent
    data class ExportToCsv(val rootScreen: RootScreen) : SettingsEvent
    data class BackupData(val rootScreen: RootScreen) : SettingsEvent
    data object SwitchTheme : SettingsEvent
    data class SetLockApp(val lockApp: Boolean) : SettingsEvent
    data class SetShowNotifications(val showNotifications: Boolean) : SettingsEvent
    data class SetShowHelpfulTips(val showHelpfulTips: Boolean) : SettingsEvent
    data class SetShowBudgetWarnings(val showBudgetWarnings: Boolean) : SettingsEvent
    data class SetShowPlannedPaymentReminders(val showPlannedPaymentReminders: Boolean) :
        SettingsEvent

    data class SetShowDailyTransactionReminders(val showDailyTransactionReminders: Boolean) :
        SettingsEvent

    data class SetShowMonthlyReviewReminders(val showMonthlyReviewReminders: Boolean) :
        SettingsEvent

    data class SetShowBackupReminders(val showBackupReminders: Boolean) : SettingsEvent

    data class SetHideCurrentBalance(val hideCurrentBalance: Boolean) : SettingsEvent
    data class SetHideIncome(val hideIncome: Boolean) : SettingsEvent
    data class SetTransfersAsIncomeExpense(val treatTransfersAsIncomeExpense: Boolean) :
        SettingsEvent

    data class SetStartDateOfMonth(val startDate: Int) : SettingsEvent
    data object DeleteCloudUserData : SettingsEvent
    data object DeleteAllUserData : SettingsEvent
    data object SwitchLanguage : SettingsEvent
}
