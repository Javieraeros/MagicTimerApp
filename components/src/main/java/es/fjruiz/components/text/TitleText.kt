package es.fjruiz.components.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TitleLargeText(text: String, modifier: Modifier = Modifier) {
    Text(text, modifier, style = MaterialTheme.typography.titleLarge)
}

@Composable
fun TitleMediumText(text: String, modifier: Modifier = Modifier) {
    Text(text, modifier, style = MaterialTheme.typography.titleMedium)
}

@Composable
fun TitleSmallText(text: String, modifier: Modifier = Modifier, textAlign: TextAlign? = null) {
    Text(text, modifier, style = MaterialTheme.typography.titleSmall, textAlign = textAlign)
}
