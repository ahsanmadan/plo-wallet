package com.ivy.poll.impl.ui

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.ivy.ui.testing.PaparazziScreenshotTest
import com.ivy.ui.testing.PaparazziTheme
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class PollScreenshotTest(
  @TestParameter
  private val theme: PaparazziTheme,
) : PaparazziScreenshotTest() {
  @Test
  fun `poll state`() {
    snapshot(theme) {
      PollUi(
        uiState = PollUiState.Content(
          poll = PollUi(
            title = "Help shape Plo",
            description = "Tell us which improvements would matter most to your personal finance workflow.",
            options = persistentListOf(
              "$1/month + taxes \"as-is\" for maintenance",
              "$5/month + taxes for new features (e.g. google drive sync, AI, etc)",
              "None, I'll uninstall",
            )
          ),
          voteLoading = false,
          voteEnabled = true,
          selectedIndex = 1,
        ),
        onEvent = {}
      )
    }
  }

  @Test
  fun `voted state`() {
    snapshot(theme) {
      PollUi(
        uiState = PollUiState.Voted,
        onEvent = {}
      )
    }
  }
}
