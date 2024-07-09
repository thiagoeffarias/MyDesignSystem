package com.farias.commoncomponents.components.buttons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.format.TextStyle

enum class ButtonLoadingState {
    Active,
    Inactive,
    Loading
}

@Composable
fun ButtonLoading(
    buttonState: ButtonLoadingState,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Button(
        onClick = { if (buttonState == ButtonLoadingState.Active) onClick() },
        enabled = buttonState != ButtonLoadingState.Inactive,
        colors = ButtonDefaults.buttonColors(
            containerColor = when (buttonState) {
                ButtonLoadingState.Active -> MaterialTheme.colorScheme.primary
                ButtonLoadingState.Inactive -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12F)
                ButtonLoadingState.Loading -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7F)
            }
        ),
        modifier = modifier
            .wrapContentSize()
            .animateContentSize() // This will animate the size changes of the Button
    ) {
        Box(contentAlignment = Alignment.Center) {
            this@Button.AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                Text(
                    text = label,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.alpha(if (buttonState != ButtonLoadingState.Loading) 1F else 0F)
                )
            }

            this@Button.AnimatedVisibility(
                visible = buttonState == ButtonLoadingState.Loading,
                enter = fadeIn(animationSpec = tween(200)),
                exit = fadeOut(animationSpec = tween(200))
            ) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewButtonLoading() {
    ButtonLoading(
        ButtonLoadingState.Active,
        label = "Loading Button",
        {}
    )
}
