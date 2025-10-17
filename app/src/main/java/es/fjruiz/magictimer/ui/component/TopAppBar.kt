package es.fjruiz.magictimer.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import es.fjruiz.components.text.TitleLargeText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopBar(title: String, icon: ImageVector, onClick: () -> Unit, modifier: Modifier = Modifier) {
    TopAppBar({
        TitleLargeText(title)
    }, modifier, navigationIcon = {
        Icon(icon, null, modifier = Modifier.clickable(onClick = onClick))
    })
}