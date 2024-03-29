package com.gobinda.notepad.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.gobinda.notepad.ui.screens.addEditNote.AddEditNoteScreen
import com.gobinda.notepad.ui.screens.noteList.NoteListScreen
import com.gobinda.notepad.ui.screens.showNote.ShowNoteScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

private const val ANIMATION_OFFSET = 500
private const val ANIMATION_DURATION = 500

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {

    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        navController = navController,
        startDestination = NoteListScreen.inputRoute
    ) {
        composable(
            route = NoteListScreen.inputRoute,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -ANIMATION_OFFSET },
                    animationSpec = tween(durationMillis = ANIMATION_DURATION)
                ) + fadeOut(animationSpec = tween(durationMillis = ANIMATION_DURATION))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -ANIMATION_OFFSET },
                    animationSpec = tween(durationMillis = ANIMATION_DURATION)
                ) + fadeIn(animationSpec = tween(durationMillis = ANIMATION_DURATION))
            }
        ) {
            NoteListScreen(navController = navController)
        }

        composable(
            route = AddOrEditNoteScreen.inputRoute,
            arguments = listOf(
                navArgument(name = AddOrEditNoteScreen.noteIdArg) {
                    type = NavType.LongType
                }
            ),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { ANIMATION_OFFSET },
                    animationSpec = tween(durationMillis = ANIMATION_DURATION)
                ) + fadeIn(animationSpec = tween(durationMillis = ANIMATION_DURATION))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { ANIMATION_OFFSET },
                    animationSpec = tween(durationMillis = ANIMATION_DURATION)
                ) + fadeOut(animationSpec = tween(durationMillis = ANIMATION_DURATION))
            }
        ) {
            AddEditNoteScreen(navController = navController)
        }

        composable(
            route = ShowNoteScreen.inputRoute,
            arguments = listOf(
                navArgument(name = ShowNoteScreen.noteIdArg) {
                    type = NavType.LongType
                }
            ),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { ANIMATION_OFFSET },
                    animationSpec = tween(durationMillis = ANIMATION_DURATION)
                ) + fadeIn(animationSpec = tween(durationMillis = ANIMATION_DURATION))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -ANIMATION_OFFSET },
                    animationSpec = tween(durationMillis = ANIMATION_DURATION)
                ) + fadeOut(animationSpec = tween(durationMillis = ANIMATION_DURATION))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -ANIMATION_OFFSET },
                    animationSpec = tween(durationMillis = ANIMATION_DURATION)
                ) + fadeIn(animationSpec = tween(durationMillis = ANIMATION_DURATION))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { ANIMATION_OFFSET },
                    animationSpec = tween(durationMillis = ANIMATION_DURATION)
                ) + fadeOut(animationSpec = tween(durationMillis = ANIMATION_DURATION))
            }
        ) {
            ShowNoteScreen(navController = navController)
        }
    }
}