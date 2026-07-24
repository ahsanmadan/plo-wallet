package com.ivy.settings.legal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivy.base.legacy.Theme
import com.ivy.design.l0_system.UI
import com.ivy.design.l0_system.style
import com.ivy.legacy.IvyWalletPreview
import com.ivy.navigation.Navigation
import com.ivy.navigation.navigation

@Composable
fun TermsAndConditionsScreenImpl() {
    LegalDocumentScreen(
        title = "Terms & Conditions",
        intro = "These terms describe how Plo should be used as a personal finance tracker.",
        sections = termsSections()
    )
}

@Composable
fun PrivacyPolicyScreenImpl() {
    LegalDocumentScreen(
        title = "Privacy Policy",
        intro = "This policy explains how Plo handles the financial information you enter in the app.",
        sections = privacySections()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LegalDocumentScreen(
    title: String,
    intro: String,
    sections: List<LegalSection>
) {
    val nav = navigation()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    BackButton(nav = nav)
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(UI.colors.pure)
                .padding(padding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                LegalHeader(title = title, intro = intro)
            }

            items(sections.size) { index ->
                LegalSectionCard(section = sections[index])
            }
        }
    }
}

@Composable
private fun BackButton(nav: Navigation) {
    IconButton(onClick = nav::back) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back"
        )
    }
}

@Composable
private fun LegalHeader(
    title: String,
    intro: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(UI.shapes.r4)
            .background(UI.colors.medium)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = UI.typo.h2.style(
                color = UI.colors.pureInverse,
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(
            text = intro,
            style = UI.typo.b2.style(
                color = UI.colors.mediumInverse,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Composable
private fun LegalSectionCard(section: LegalSection) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(UI.shapes.r4)
            .background(UI.colors.medium)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = section.title,
            style = UI.typo.b1.style(
                color = UI.colors.pureInverse,
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(
            text = section.body,
            style = UI.typo.b2.style(
                color = UI.colors.mediumInverse,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

private data class LegalSection(
    val title: String,
    val body: String
)

private fun termsSections(): List<LegalSection> = listOf(
    LegalSection(
        title = "Personal finance tool",
        body = "Plo is designed to help you record transactions, accounts, budgets, reports, loans, and savings goals. The app is not a bank, broker, tax advisor, accountant, or financial advisor."
    ),
    LegalSection(
        title = "Your responsibility",
        body = "You are responsible for checking the accuracy of the information you enter, including balances, categories, currencies, budgets, and exported files. Decisions made from the app's summaries remain your responsibility."
    ),
    LegalSection(
        title = "Local and offline use",
        body = "Plo can be used with an offline account. Local data is stored on your device, and removing the app or clearing its data may remove your records unless you have exported or backed them up."
    ),
    LegalSection(
        title = "No guarantee",
        body = "The app is provided as a practical tracking tool. We try to keep it reliable, but we do not guarantee that every calculation, report, notification, import, export, or sync-related feature will always be uninterrupted or error-free."
    ),
    LegalSection(
        title = "Changes",
        body = "These terms may be updated as Plo changes. Continuing to use the app after updates means you accept the latest version shown inside the app."
    )
)

private fun privacySections(): List<LegalSection> = listOf(
    LegalSection(
        title = "Information you add",
        body = "Plo may store the financial records you enter, such as transactions, accounts, categories, budgets, loans, currency settings, app preferences, and account names."
    ),
    LegalSection(
        title = "Storage",
        body = "For offline accounts, your data is stored locally on your device. The app package name is kept unchanged for compatibility with the existing Android and Firebase configuration, but Plo does not require you to create an online account to track local finances."
    ),
    LegalSection(
        title = "Exports and backups",
        body = "When you export, import, share, or back up data, you control where those files go. Please protect exported files because they may contain private financial information."
    ),
    LegalSection(
        title = "Device permissions",
        body = "Plo may request permissions only when needed for app features, such as notifications, file export, import, or backup workflows. You can manage permissions from Android system settings."
    ),
    LegalSection(
        title = "Privacy-first use",
        body = "Avoid entering passwords, bank login credentials, card security codes, or other secrets into transaction notes or custom names. Use Plo for tracking, not for storing sensitive authentication details."
    )
)

@Preview
@Composable
private fun TermsPreview() {
    IvyWalletPreview(Theme.DARK) {
        LegalDocumentScreen(
            title = "Terms & Conditions",
            intro = "These terms describe how Plo should be used as a personal finance tracker.",
            sections = termsSections()
        )
    }
}

@Preview
@Composable
private fun PrivacyPreview() {
    IvyWalletPreview(Theme.DARK) {
        LegalDocumentScreen(
            title = "Privacy Policy",
            intro = "This policy explains how Plo handles the financial information you enter in the app.",
            sections = privacySections()
        )
    }
}
