package es.fjruiz.components.button

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.fjruiz.components.text.BodyMediumText

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
    Button(onClick, modifier, enabled) {
        BodyMediumText(text)
    }
}