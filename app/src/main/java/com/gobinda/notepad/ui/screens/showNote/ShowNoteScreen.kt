package com.gobinda.notepad.ui.screens.showNote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gobinda.notepad.ui.navigation.AddOrEditNoteScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowNoteScreen(
    navController: NavController,
    viewModel: ShowNoteViewModel = hiltViewModel()
) {

    val currentNote = viewModel.currentNote.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                ),
                title = { },
                actions = {
                    IconButton(
                        onClick = {
                            val noteId = currentNote.value?.id ?: return@IconButton
                            val inputMap = mapOf(AddOrEditNoteScreen.noteIdArg to noteId)
                            val editScreenRoute = AddOrEditNoteScreen.outputRoute(inputMap)
                            navController.navigate(editScreenRoute)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            currentNote.value?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = it.title,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Light,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = it.content,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Light,
                )
            }
        }
    }
}