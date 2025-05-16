package cz.eman.android.interview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.core.view.WindowCompat
import cz.eman.android.interview.repository.Repository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MaterialTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(
                            insets = WindowInsets.navigationBars,
                        ),
                    content = { paddingValues ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {
                            content(MainViewModel(Repository())) // DI will be done in next MR
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun content(
    viewModel: MainViewModel,
) {
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Blue,
            text = "Hello eMan candidate",
        )

        val data = viewModel.state.collectAsState()

        Button(
            onClick = {
                viewModel.onClick()
            }
        ) {
            Text(text = "Generate names with length ${data.value.number+1}")
        }

        Column {
            data.value.names.forEachIndexed { index, name ->
                Text(
                    text = name
                )

                if (index < data.value.names.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}
