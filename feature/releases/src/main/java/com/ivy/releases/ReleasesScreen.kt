package com.ivy.releases

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ivy.navigation.navigation
import com.ivy.ui.R
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ReleasesScreenImpl() {
    ReleasesUi()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReleasesUi() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopAppBarTitle(title = stringResource(R.string.releases))
                },
                navigationIcon = {
                    BackButton()
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = innerPadding,
        ) {
            items(localPloReleases()) {
                ReleaseInfoCard(releaseInfo = it)
            }
        }
    }
}

@Composable
private fun TopAppBarTitle(title: String) {
    Text(
        text = title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun BackButton() {
    val nav = navigation()

    IconButton(onClick = {
        nav.back()
    }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReleaseInfoCard(
    releaseInfo: ReleaseInfo,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier,
        onClick = {}
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            ReleaseInfoRow(releaseInfo = releaseInfo)

            if (releaseInfo.releaseCommits.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))

                for (commit in releaseInfo.releaseCommits) {
                    Text(text = "• $commit")
                }
            }
        }
    }
}

@Composable
private fun ReleaseInfoRow(
    releaseInfo: ReleaseInfo,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ReleaseName(info = releaseInfo.releaseName)
        Spacer(modifier = Modifier.weight(1f))
        ReleaseDate(info = releaseInfo.releaseDate)
    }
}

@Composable
private fun ReleaseName(
    info: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = info,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun ReleaseDate(
    info: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = info,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold
    )
}

private fun localPloReleases() = persistentListOf(
    ReleaseInfo(
        releaseName = "Plo Rebrand Cleanup",
        releaseUrl = "",
        releaseDate = "2026.07.24",
        releaseCommits = persistentListOf(
            "Updated visible app branding to Plo.",
            "Removed legacy community and repository promotional links.",
            "Added local Terms & Conditions and Privacy Policy screens."
        )
    ),
    ReleaseInfo(
        releaseName = "Personal Finance Core",
        releaseUrl = "",
        releaseDate = "2026.07.17",
        releaseCommits = persistentListOf(
            "Kept the stable finance tracking foundation.",
            "Preserved local data, accounts, budgets, reports, loans, and planned payments."
        )
    )
)
