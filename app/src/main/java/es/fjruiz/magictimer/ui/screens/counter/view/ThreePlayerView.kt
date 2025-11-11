package es.fjruiz.magictimer.ui.screens.counter.view

import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import es.fjruiz.commoncompose.ext.rememberSaveableState
import es.fjruiz.commoncompose.utils.LockScreenOrientation
import es.fjruiz.magictimer.ui.screens.counter.CounterIntent
import es.fjruiz.magictimer.ui.component.card.CounterCard
import es.fjruiz.magictimer.ui.component.card.CounterCardModel
import es.fjruiz.magictimer.ui.util.rotateLayout

private const val firstPlayerId = "firstPlayerId"
private const val secondPlayerId = "secondPlayerId"
private const val thirdPlayerId = "thirdPlayerId"
private const val pauseButtonId = "pauseButtonId"

@Composable
fun ThreePlayerView(
    firstCounterModel: CounterCardModel,
    secondCounterModel: CounterCardModel,
    thirdCounterModel: CounterCardModel,
    handleIntent: (CounterIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    ConstraintLayout(getConstraintSet(),
        modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        CounterCard(firstCounterModel, handleIntent,
            Modifier.layoutId(firstPlayerId).rotateLayout())

        CounterCard(secondCounterModel, handleIntent, Modifier.layoutId(secondPlayerId).rotateLayout(false))

        CounterCard(thirdCounterModel, handleIntent, Modifier.layoutId(thirdPlayerId))

        Button(
            onClick = {
                handleIntent(CounterIntent.OnPauseClicked)
            },
            Modifier
                .layoutId(pauseButtonId)
                .size(48.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp)
        ) {
            Image(Icons.Default.Pause, contentDescription = "Pause button")
        }
    }

}

private fun getConstraintSet() =  ConstraintSet {
    val (firstPlayer, secondPlayer, thirdPlayer, pauseButton) = createRefsFor(
        firstPlayerId,
        secondPlayerId,
        thirdPlayerId,
        pauseButtonId
    )

    constrain(firstPlayer) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        bottom.linkTo(thirdPlayer.top)
        end.linkTo(secondPlayer.start)
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }

    constrain(secondPlayer) {
        top.linkTo(parent.top)
        start.linkTo(firstPlayer.end)
        bottom.linkTo(thirdPlayer.top)
        end.linkTo(parent.end)
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }

    constrain(thirdPlayer) {
        top.linkTo(firstPlayer.bottom)
        start.linkTo(parent.start)
        bottom.linkTo(parent.bottom)
        end.linkTo(parent.end)
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }

    constrain(pauseButton) {
        centerTo(parent)
    }
}
