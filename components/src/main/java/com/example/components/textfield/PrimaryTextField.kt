package es.fjruiz.components.textfield

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.fjruiz.components.text.BodyMediumText

@Composable
fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String = ""
) {
    OutlinedTextField(value, onValueChange, modifier, enabled, label = {
            BodyMediumText(label)
        }
    )
}

@Preview
@Composable
private fun PrimaryTextFieldPreview() {
    PrimaryTextField("", {}, label = "Prueba")
}