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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import es.fjruiz.commoncompose.utils.LockScreenOrientation
import es.fjruiz.components.card.CounterCard
import es.fjruiz.components.card.CounterCardModel
import es.fjruiz.magictimer.ui.util.rotateLayout

private const val firstPlayerId = "firstPlayerId"
private const val secondPlayerId = "secondPlayerId"
private const val thirdPlayerId = "thirdPlayerId"
private const val fourthPlayerId = "fourthPlayerId"
private const val pauseButtonId = "pauseButtonId"

@Composable
fun FourPlayerView(
    firstCounterModel: CounterCardModel,
    secondCounterModel: CounterCardModel,
    thirdCounterModel: CounterCardModel,
    fourthCounterModel: CounterCardModel,
    modifier: Modifier = Modifier
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    ConstraintLayout(
        getConstraintSet(),
        modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        CounterCard(
            firstCounterModel,
            modifier = Modifier
                .rotateLayout()
                .layoutId(firstPlayerId)
        )

        CounterCard(
            secondCounterModel,
            modifier = Modifier
                .rotateLayout(false)
                .layoutId(secondPlayerId)
        )

        CounterCard(
            thirdCounterModel,
            modifier = Modifier
                .layoutId(thirdPlayerId)
                .rotateLayout()
        )

        CounterCard(
            fourthCounterModel,
            modifier = Modifier
                .layoutId(fourthPlayerId)
                .rotateLayout(false)
        )

        Button(
            onClick = {
                // TODO: Pause
            },
            Modifier
                .layoutId(pauseButtonId)
                .rotateLayout()
        ) {
            Image(Icons.Default.Pause, contentDescription = "Pause button")
        }
    }
}

private fun getConstraintSet(): ConstraintSet {
    return ConstraintSet {
        val (firstPlayer, secondPlayer, thirdPlayer, fourthPlayer, pauseButton) = createRefsFor(
            firstPlayerId,
            secondPlayerId,
            thirdPlayerId,
            fourthPlayerId,
            pauseButtonId
        )
        constrain(pauseButton) {
            centerTo(parent)
        }
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
            bottom.linkTo(fourthPlayer.top)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
        constrain(thirdPlayer) {
            top.linkTo(firstPlayer.bottom)
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
            end.linkTo(fourthPlayer.start)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }

        constrain(fourthPlayer) {
            top.linkTo(secondPlayer.bottom)
            start.linkTo(thirdPlayer.end)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }

    }
}