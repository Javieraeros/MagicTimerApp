package es.fjruiz.components.textfield

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Composable
fun DigitTextField(
    value: TextFieldState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    OutlinedTextField(
        value,
        modifier,
        enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview
@Composable
private fun DigitTextFieldPreview() {
    DigitTextField(TextFieldState("test"))
}