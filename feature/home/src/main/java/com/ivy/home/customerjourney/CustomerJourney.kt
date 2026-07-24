package com.ivy.home.customerjourney

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivy.design.l0_system.GreenLight
import com.ivy.design.l0_system.Ivy
import com.ivy.design.l0_system.Orange
import com.ivy.design.l0_system.Red
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
import com.ivy.wallet.ui.theme.modal.IvyModal
import com.ivy.wallet.ui.theme.modal.ModalSet
import com.ivy.wallet.ui.theme.modal.ModalTitle
import kotlinx.collections.immutable.ImmutableList
import java.util.UUID

@Composable
fun CustomerJourney(
    customerJourneyCards: ImmutableList<CustomerJourneyCardModel>,
    modifier: Modifier = Modifier,
    onDismiss: (CustomerJourneyCardModel) -> Unit,
    onSnooze: (CustomerJourneyCardModel) -> Unit,
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
                onSnooze = {
                    onSnooze(card)
                },
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
    onSnooze: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    onCTA: () -> Unit,
) {
    val accentColor = cardData.alertType.accentColor()

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

        Spacer(Modifier.width(8.dp))

        CompactCustomerJourneyControls(
            cardData = cardData,
            accentColor = accentColor,
            onSnooze = onSnooze,
            onDismiss = onDismiss,
        )
    }
}

@Composable
fun BoxScope.CustomerJourneyNotificationCenter(
    visible: Boolean,
    customerJourneyCards: ImmutableList<CustomerJourneyCardModel>,
    onDismiss: (CustomerJourneyCardModel) -> Unit,
    onSnooze: (CustomerJourneyCardModel) -> Unit,
    onClose: () -> Unit,
) {
    val ivyContext = ivyWalletCtx()
    val nav = navigation()

    if (LocalContext.current is RootScreen) {
        val rootScreen = rootScreen()

        IvyModal(
            id = rememberNotificationCenterId(),
            visible = visible,
            dismiss = onClose,
            PrimaryAction = {
                ModalSet(
                    label = stringResource(R.string.done),
                    onClick = onClose
                )
            }
        ) {
            Spacer(Modifier.height(28.dp))

            ModalTitle(text = stringResource(R.string.plo_notification_center_title))

            Spacer(Modifier.height(20.dp))

            if (customerJourneyCards.isEmpty()) {
                EmptyNotificationCenter()
            } else {
                PloAlertType.entries
                    .map { type -> type to customerJourneyCards.filter { it.alertType == type } }
                    .filter { (_, cards) -> cards.isNotEmpty() }
                    .forEach { (type, cards) ->
                        NotificationSectionTitle(type = type)

                        cards.forEach { card ->
                            CompactCustomerJourneyCard(
                                modifier = Modifier.padding(bottom = 12.dp),
                                cardData = card,
                                onSnooze = {
                                    onSnooze(card)
                                    onClose()
                                },
                                onDismiss = {
                                    onDismiss(card)
                                    onClose()
                                },
                                onCTA = {
                                    card.onAction(nav, ivyContext, rootScreen)
                                    onClose()
                                }
                            )
                        }
                    }
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun rememberNotificationCenterId(): UUID {
    return androidx.compose.runtime.remember { UUID.randomUUID() }
}

@Composable
private fun EmptyNotificationCenter() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.plo_notification_center_empty_title),
            style = UI.typo.b2.style(
                color = UI.colors.pureInverse,
                fontWeight = FontWeight.ExtraBold,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = stringResource(R.string.plo_notification_center_empty_body),
            style = UI.typo.c.style(
                color = Gray,
                fontWeight = FontWeight.Medium,
            ),
        )
    }
}

@Composable
private fun NotificationSectionTitle(type: PloAlertType) {
    Text(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
        text = type.label(),
        style = UI.typo.c.style(
            color = type.accentColor(),
            fontWeight = FontWeight.ExtraBold,
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
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

@Composable
private fun CompactCustomerJourneyControls(
    cardData: CustomerJourneyCardModel,
    accentColor: Color,
    onSnooze: () -> Unit,
    onDismiss: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.End) {
        CompactTextAction(
            text = stringResource(R.string.plo_alert_later),
            icon = R.drawable.ic_clock,
            accentColor = accentColor,
            onClick = onSnooze,
        )

        if (cardData.hasDismiss) {
            Spacer(Modifier.height(8.dp))

            CompactTextAction(
                text = stringResource(R.string.plo_alert_dismiss),
                icon = R.drawable.ic_dismiss,
                accentColor = Gray,
                onClick = onDismiss,
            )
        }
    }
}

@Composable
private fun CompactTextAction(
    text: String,
    icon: Int,
    accentColor: Color,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clip(UI.shapes.r2)
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IvyIcon(
            icon = icon,
            tint = accentColor,
        )

        Spacer(Modifier.width(4.dp))

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

private fun PloAlertType.accentColor(): Color {
    return when (this) {
        PloAlertType.CRITICAL -> Red
        PloAlertType.REMINDER -> Orange
        PloAlertType.INSIGHT -> Ivy
        PloAlertType.TIP -> GreenLight
    }
}

private fun PloAlertType.label(): String {
    return when (this) {
        PloAlertType.CRITICAL -> "Critical"
        PloAlertType.REMINDER -> "Reminder"
        PloAlertType.INSIGHT -> "Insight"
        PloAlertType.TIP -> "Tip"
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
            onSnooze = {},
            onDismiss = {}
        )
    }
}
