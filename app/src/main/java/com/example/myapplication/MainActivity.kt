package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    var imageResource = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon
        3 -> R.drawable.lemonade
        else -> R.drawable.empty_glass
    }

    var contentDescription = when (currentStep) {
        1 -> R.string.lemon_tree_description
        2 -> R.string.lemon_description
        3 -> R.string.lemonade_description
        else -> R.string.empty_glass_description
    }

    var textPrompt = when (currentStep) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.lemonade
        else -> R.string.empty_glass
    }

    TextAndImage(
        imageResourceId = imageResource,
        contentDescriptionId = contentDescription,
        onClick = {
            when (currentStep) {
                1 -> {
                    currentStep = 2
                    squeezeCount = (2..4).random()
                }
                2 -> {
                    if (squeezeCount > 0) {
                        squeezeCount--
                    }
                    if (squeezeCount == 0) {
                        currentStep = 3
                    }
                }
                3 -> currentStep = 4
                4 -> currentStep = 1
            }
        },
        textResourceID = textPrompt,
        currentStep = currentStep,
        squeezeCount = squeezeCount
    )
}

@Composable
fun TextAndImage(
    imageResourceId: Int,
    contentDescriptionId: Int,
    onClick: () -> Unit,
    textResourceID: Int,
    currentStep: Int,
    squeezeCount: Int
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResourceId),
            contentDescription = stringResource(contentDescriptionId),
            modifier = Modifier.clickable { onClick() }
        )
        Text(
            text = stringResource(textResourceID)
        )
    }
}