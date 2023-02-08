package dev.srijith.sample.composenavigations.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter

class LoginPresenter(private val navigatorPresenter: NavigatorPresenter) {
    val username: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = mutableStateOf("")

    fun onUsernameChange(text: String) {
        username.value = text
    }

    fun onPasswordChange(text: String) {
        password.value = text
    }

    fun onLoginButtonClicked() {
        if (username.value.isNotEmpty() && password.value.isNotEmpty()) {
            navigatorPresenter.onLoginClicked(username.value)
        }
    }

}