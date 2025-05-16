package cz.eman.android.interview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.core.view.WindowCompat
import androidx.room.Room
import cz.eman.android.interview.database.AppDatabase
import cz.eman.android.interview.repository.Repository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

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
                            content(MainViewModel(Repository(db))) // DI will be done in next MR
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

        var jmeno: String = ""
        var prijmeni: String = ""

        Row {
            Column {
                Text("Jmeno")
                TextField(jmeno, onValueChange = {
                    jmeno = it
                })
            }
            Column {
                Text("Prijmeni")
                TextField("", onValueChange = {
                    prijmeni = it
                })
            }
        }


        Button(
            onClick = {
                viewModel.onClick(
                    jmeno = jmeno,
                    prijmeni = prijmeni,
                )
            }
        ) {
            Text(text = "Ulozit")
        }


    }
}
