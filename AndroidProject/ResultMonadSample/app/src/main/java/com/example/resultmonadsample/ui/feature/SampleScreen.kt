package com.example.resultmonadsample.ui.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.resultmonadsample.App
import com.example.resultmonadsample.R
import com.example.resultmonadsample.ui.uistate.SampleScreenUiState
import com.example.resultmonadsample.ui.viewmodel.SampleViewModel
import timber.log.Timber


@Composable
fun SampleScreen(viewModel: SampleViewModel = viewModel()) {
    val data by viewModel.uiState.collectAsState()
    SampleContent(
        data = data,
        onRefreshTap = {},
        onViewAllTap = {},
        onCloseTap = {},
        onFollowTap = {}
    )
    if (data.isErrorDialogShown) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.White),
        ) {
            Text(text = "Error", modifier = Modifier.align(Alignment.Center))
            AlertDialog(
                onDismissRequest = {},
                confirmButton = {
                    TextButton(
                        onClick = {}
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {}
                    ) {
                        Text("Cancel")
                    }
                },
                title = {
                    Text("Error Dialog")
                },
                text = {
                    Text("message : ${data.errorDialogType}")
                },
            )
        }
    }

    if (data.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.White),
        ) {
            Text(text = "Loading", modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun SampleContent(
    data: SampleScreenUiState,
    onRefreshTap: () -> Unit,
    onViewAllTap: () -> Unit,
    onCloseTap: (SampleScreenUiState) -> Unit,
    onFollowTap: (SampleScreenUiState) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = "Who to Follow", color = Color.DarkGray, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Refresh",
                color = Color.DarkGray,
                fontSize = 16.sp,
                modifier = Modifier.clickable { onRefreshTap() }
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "View All",
                color = Color.DarkGray,
                fontSize = 16.sp,
                modifier = Modifier.clickable { onViewAllTap() }
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Spacer(modifier = Modifier.width(8.dp))
        SampleDataBox(
            data = data,
            onCloseTap = onCloseTap,
            onFollowTap = onFollowTap
        )
    }
}

@Composable
fun SampleDataBox(
    data: SampleScreenUiState,
    onCloseTap: (SampleScreenUiState) -> Unit = {},
    onFollowTap: (SampleScreenUiState) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            modifier = Modifier.weight(0.3f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .weight(0.6f)
        ) {
            Row {
                Text(
                    modifier = Modifier
                        .padding(end = 12.dp),
                    text = data.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = data.note,
                    color = Color.Gray,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Button(
                onClick = { onFollowTap(data) },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.LightGray,
                    contentColor = Color.Black,
                    disabledContentColor = Color.Gray
                )
            ) {
                Text(text = "Follow")
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_close_24),
            contentDescription = "",
            modifier = Modifier
                .weight(0.1f)
                .clickable { onCloseTap(data) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UseBoxPreview() {
    SampleDataBox(
        SampleScreenUiState(
            name = "Erik Meijer",
            note = "@headinthebox",
        )
    )
}