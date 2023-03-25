package com.country.countryLlst.navigation;

import android.os.Parcelable
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver

class Navigator<T : Parcelable> private constructor(
    initialBackStack: List<T>,
    backDispatcher: OnBackPressedDispatcher
) {
    constructor(
        initial: T,
        backDispatcher: OnBackPressedDispatcher
    ) : this(listOf(initial), backDispatcher)

    private val backStack = initialBackStack.toMutableStateList()
    private val backCallback = object : OnBackPressedCallback(canGoBack()) {
        override fun handleOnBackPressed() {
            back()
        }
    }.also { callback ->
        backDispatcher.addCallback(callback)
    }
    val current: T get() = backStack.last()

    fun back() {
        backStack.removeAt(backStack.lastIndex)
        backCallback.isEnabled = canGoBack()
    }

    fun navigate(destination: T) {
        backStack += destination
        backCallback.isEnabled = canGoBack()
    }

    private fun canGoBack(): Boolean = backStack.size > 1

    companion object {

        fun <T : Parcelable> saver(backDispatcher: OnBackPressedDispatcher) =
            listSaver<Navigator<T>, T>(
                save = { navigator -> navigator.backStack.toList() },
                restore = { backstack -> Navigator(backstack, backDispatcher) }
            )
    }
}


@Composable
fun backHandler(
    enabled: Boolean = true,
    onBack: () -> Unit
) {
    val backCallback = remember(onBack) {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }
    }
    LaunchedEffect((enabled)) {
        backCallback.isEnabled = enabled
    }

    val dispatcher = BackDispatcherAmbient.current
    dispatcher.addCallback(backCallback)
    DisposableEffect(backCallback) {
        onDispose {
            backCallback.remove()
        }
    }
}

internal val BackDispatcherAmbient = compositionLocalOf<OnBackPressedDispatcher> {
    error("No Back Dispatcher provided")
}