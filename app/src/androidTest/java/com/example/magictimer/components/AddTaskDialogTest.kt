package es.fjruiz.magictimer.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import es.fjruiz.magictimer.ui.screens.tasklist.AddTaskDialog
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddTaskDialogTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            AddTaskDialog(true, onConfirmClicked = {}) {

            }
        }
    }

    @Test
    fun whenDialogGetATrue_thenShowDialog() {
        composeTestRule.onNodeWithTag("dialog").assertIsDisplayed()
    }
}