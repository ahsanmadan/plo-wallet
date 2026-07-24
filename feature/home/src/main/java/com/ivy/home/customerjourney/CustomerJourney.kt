package com.ivy.home.customerjourney

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivy.design.l0_system.UI
import com.ivy.design.l0_system.style
import com.ivy.domain.RootScreen
import com.ivy.legacy.ivyWalletCtx
import com.ivy.legacy.rootScreen
import com.ivy.navigation.IvyPreview
import com.ivy.navigation.navigation
import com.ivy.ui.R
import com.ivy.wallet.ui.theme.Gray
import com.ivy.wallet.ui.theme.components.IvyIcon
import kotlinx.collections.immutable.ImmutableList

@Composable
fun CustomerJourney(
    customerJourneyCards: ImmutableList<CustomerJourneyCardModel>,
    modifier: Modifier = Modifier,
    onDismiss: (CustomerJourneyCardModel) -> Unit,
) {
    val ivyContext = ivyWalletCtx()
    val nav = navigation()
    // Check is added for Paparazzi Test where context is different
    if (LocalContext.current is RootScreen) {
        val rootScreen = rootScreen()

        customerJourneyCards.firstOrNull()?.let { card ->
            Spacer(Modifier.height(12.dp))

            CompactCustomerJourneyCard(
                modifier = modifier,
                cardData = card,
                onDismiss = {
                    onDismiss(card)
                }
            ) {
                card.onAction(nav, ivyContext, rootScreen)
            }
        }
    } else {
        Box(modifier)
    }
}

@Composable
fun CompactCustomerJourneyCard(
    cardData: CustomerJourneyCardModel,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    onCTA: () -> Unit,
) {
    val accentColor = cardData.background.startColor

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(UI.shapes.r4)
            .background(UI.colors.pure)
            .border(1.dp, UI.colors.medium, UI.shapes.r4)
            .clickable {
                onCTA()
            }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(56.dp)
                .clip(UI.shapes.rFull)
                .background(accentColor)
        )

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = cardData.title,
                style = UI.typo.b2.style(
                    fontWeight = FontWeight.ExtraBold,
                    color = UI.colors.pureInverse,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = cardData.description.compactDescription(),
                style = UI.typo.c.style(
                    fontWeight = FontWeight.Medium,
                    color = Gray,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            if (cardData.cta != null) {
                Spacer(Modifier.height(10.dp))

                CompactCustomerJourneyAction(
                    text = cardData.cta,
                    icon = cardData.ctaIcon,
                    accentColor = accentColor,
                    onClick = onCTA,
                    modifier = Modifier.testTag("cta_prompt_${cardData.id}")
                )
            }
        }

        if (cardData.hasDismiss) {
            Spacer(Modifier.width(8.dp))

            IvyIcon(
                modifier = Modifier
                    .size(40.dp)
                    .clip(UI.shapes.rFull)
                    .clickable {
                        onDismiss()
                    }
                    .padding(10.dp),
                icon = R.drawable.ic_dismiss,
                tint = Gray,
                contentDescription = "prompt_dismiss",
            )
        }
    }
}

@Composable
private fun CompactCustomerJourneyAction(
    text: String,
    icon: Int,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(UI.shapes.r2)
            .background(UI.colors.medium)
            .clickable {
                onClick()
            }
            .padding(horizontal = 10.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IvyIcon(
            icon = icon,
            tint = accentColor,
        )

        Spacer(Modifier.width(6.dp))

        Text(
            text = text,
            style = UI.typo.c.style(
                color = UI.colors.pureInverse,
                fontWeight = FontWeight.ExtraBold,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

private fun String.compactDescription(): String {
    val firstParagraph = lineSequence()
        .map { it.trim() }
        .firstOrNull { it.isNotBlank() }
        ?: this
    val firstSentenceEnd = firstParagraph.indexOf(". ")
    return if (firstSentenceEnd > 0) {
        firstParagraph.take(firstSentenceEnd + 1)
    } else {
        firstParagraph
    }
}

@Preview
@Composable
private fun PreviewCard() {
    IvyPreview {
        CompactCustomerJourneyCard(
            cardData = CustomerJourneyCardsProvider.adjustBalanceCard(),
            onCTA = { },
            onDismiss = {}
        )
    }
}
