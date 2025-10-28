package es.fjruiz.magictimer.ui.screens.counter.view

import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import es.fjruiz.commoncompose.utils.LockScreenOrientation
import es.fjruiz.magictimer.ui.component.card.CounterCard
import es.fjruiz.magictimer.ui.component.card.CounterCardModel
import es.fjruiz.magictimer.ui.screens.counter.CounterIntent
import es.fjruiz.magictimer.ui.util.rotateLayout

private const val firstPlayerId = "firstPlayerId"
private const val secondPlayerId = "secondPlayerId"
private const val pauseButtonId = "pauseButtonId"

@Composable
fun TwoPlayerView(
    firstCounterModel: CounterCardModel,
    secondCounterModel: CounterCardModel,
    handleIntent: (CounterIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    ConstraintLayout(getConstraintSet(),
        modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        CounterCard(firstCounterModel, handleIntent, Modifier.layoutId(firstPlayerId).rotate(180F))

        CounterCard(secondCounterModel, handleIntent, Modifier.layoutId(secondPlayerId))

        Button(
            onClick = {
                handleIntent(CounterIntent.OnPauseClicked)
            }, Modifier.layoutId(pauseButtonId)) {
            Image(Icons.Default.Pause, contentDescription = "Pause button")
        }
    }

}

private fun getConstraintSet() =  ConstraintSet {
    val (firstPlayer, secondPlayer, pauseButton) = createRefsFor(
        firstPlayerId,
        secondPlayerId,
        pauseButtonId
    )

    constrain(firstPlayer) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(secondPlayer.top)
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }

    constrain(secondPlayer) {
        top.linkTo(firstPlayer.bottom)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }

    constrain(pauseButton) {
        centerTo(parent)
    }
}