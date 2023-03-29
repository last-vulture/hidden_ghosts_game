package com.testtask.hiddenghosts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testtask.hiddenghosts.ui.theme.CellBackground
import com.testtask.hiddenghosts.ui.theme.GameBackground

@Composable
fun HiddenGhostsApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        GameScreen()
    }
}

@Composable
fun SplashScreen() {
    Box(contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier
                .size(120.dp),
            painter = painterResource(id = R.drawable.ic_game_icon),
            contentDescription = null
        )
    }
}

@Composable
fun StartGameScreen() {
    Box(contentAlignment = Alignment.Center) {
        OutlinedButton(onClick = {
            //nothing
        }) {
            Text(text = "Start game", color = GameBackground)
        }
    }
}

@Composable
fun NextLevelScreen() {
    Box(contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "123", fontSize = 72.sp)
            Text(text = "Score", fontSize = 32.sp)
        }

        OutlinedButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {
                //nothing
            }
        ) {
            Text(text = "Next level", color = GameBackground)
        }
    }
}

@Composable
fun GameScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "123")
        OutlinedButton(
            onClick = {
                //nothing
            }
        ) {
            Text(text = "Restart", color = GameBackground)
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(30.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items((1..16).toList()) {
                Box(Modifier.background(CellBackground).aspectRatio(1f)) {
                    Text(text = "$it")
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun AppPreview() {
    HiddenGhostsApp()
}