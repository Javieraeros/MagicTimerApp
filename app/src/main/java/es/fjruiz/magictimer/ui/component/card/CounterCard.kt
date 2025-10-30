package es.fjruiz.magictimer.ui.component.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import coil3.compose.AsyncImage
import es.fjruiz.magictimer.R
import es.fjruiz.components.button.PrimaryButton
import es.fjruiz.magictimer.ui.base.HandleIntent
import es.fjruiz.magictimer.ui.screens.counter.CounterIntent

const val jace =
    "https://images.ctfassets.net/s5n2t79q9icq/5Z5BZ90db9laZwDh9hO7RP/83ea76b645bfaad7973099d009f67356/jace-beleren-1920.jpg?q=80"
const val chandra =
    "https://images.ctfassets.net/s5n2t79q9icq/4Ccsre2U4012DgUfGcKYJp/ab0c9dabdb0c2869e4755f8d6c872ed2/chandra-nalaar-1920.jpg?q=80"
const val vivien =
    "https://images.ctfassets.net/s5n2t79q9icq/XbA9mWj3ix8WYrv464zoJ/4cd267cb17f450b5aa4dcac205187f93/vivien-reid-1920.jpg?q=80"
const val ajani =
    "https://images.ctfassets.net/s5n2t79q9icq/2t3q9lomkTzsNhudK7mbCW/5f73e67aadfdaa94fcd08988dcfca558/ajani-1920.jpg?q=80"

val gold = Color(0xFFD4AF37)
val transparentGray = Color(0x55888888)

private const val backgroundImageId = "backgroundImage"
private const val timeTextId = "timeText"
private const val firstStarId = "firstStar"
private const val secondStarId = "secondStar"
private const val turnButtonId = "turnButtonId"
private const val priorityButtonId = "priorityButtonId"

@Composable
fun CounterCard(
    counterCardModel: CounterCardModel,
    handleIntent: HandleIntent<CounterIntent>,
    modifier: Modifier = Modifier
) {
    val border = if (counterCardModel.hasPriority) {
        BorderStroke(5.dp, gold)
    } else {
        BorderStroke(5.dp, Color.Transparent)
    }

    ConstraintLayout(
        constraintSet = getConstraintSet(),
        modifier
            .border(border, RoundedCornerShape(12.dp))
            .padding(4.dp)
    ) {

        AsyncImage(
            model = counterCardModel.image,
            contentDescription = "",
            alpha = 0.8F,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(
                    MaterialTheme.shapes.medium
                )
                .layoutId(backgroundImageId)
        )
        Text(
            counterCardModel.time,
            fontSize = 72.sp,
            color = Color.White,
            modifier = Modifier.layoutId(timeTextId)
        )

        Icon(
            Icons.Default.Star,
            contentDescription = "First star",
            tint = getFirstStarTint(counterCardModel.stars),
            modifier = Modifier
                .layoutId(firstStarId)
                .background(transparentGray)
        )

        Icon(
            Icons.Default.Star,
            contentDescription = "Second star",
            tint = getSecondStarTint(counterCardModel.stars),
            modifier = Modifier
                .layoutId(secondStarId)
                .background(transparentGray)
        )

        PrimaryButton(
            stringResource(R.string.take_priority),
            {
                handleIntent(CounterIntent.OnTakePriorityClicked(counterCardModel.playerId))
            },
            Modifier.layoutId(priorityButtonId),
            counterCardModel.hasPriority.not()
        )

        PrimaryButton(
            stringResource(R.string.take_turn),
            {
                handleIntent(CounterIntent.OnTakeTurnClicked(counterCardModel.playerId))
            },
            Modifier.layoutId(turnButtonId),
            counterCardModel.hasTurn.not()
        )
    }
}

private fun getConstraintSet(): ConstraintSet {
    return ConstraintSet {
        val (background, timeText, firstStar, secondStar, priorityButton, turnButton) = createRefsFor(
            backgroundImageId,
            timeTextId,
            firstStarId,
            secondStarId,
            priorityButtonId,
            turnButtonId
        )
        constrain(background) {
            centerTo(parent)
        }
        constrain(timeText) {
            centerTo(parent)
        }
        constrain(firstStar) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(secondStar.start)
        }
        constrain(secondStar) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            start.linkTo(firstStar.end)
        }

        constrain(priorityButton){
            start.linkTo(parent.start)
            end.linkTo(turnButton.start)
            bottom.linkTo(parent.bottom)
        }

        constrain(turnButton){
            start.linkTo(priorityButton.end)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }

    }
}

private fun getFirstStarTint(starEnum: StarEnum): Color {
    return if (starEnum == StarEnum.ONE || starEnum == StarEnum.TWO) {
        gold
    } else {
        Color.Black
    }
}

private fun getSecondStarTint(starEnum: StarEnum): Color {
    return if (starEnum == StarEnum.TWO) {
        gold
    } else {
        Color.Black
    }
}

@Preview
@Composable
private fun CounterCardPreview() {
    CounterCard(
        CounterCardModel(
            time = "2:00",
            image = jace,
            hasTurn = false,
            hasPriority = true,
            stars = StarEnum.ONE,
        ), {

        }
    )
}