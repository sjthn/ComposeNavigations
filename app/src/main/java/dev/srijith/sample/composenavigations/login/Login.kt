package dev.srijith.sample.composenavigations.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.srijith.sample.composenavigations.login.dependency.LoginDependencyComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(loginDependencyComponent: LoginDependencyComponent) {
    val loginPresenter = loginDependencyComponent.loginPresenter
    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {

        Column {
            TextField(
                value = loginPresenter.username.value,
                onValueChange = loginPresenter::onUsernameChange,
                label = { Text("username") })
            TextField(
                value = loginPresenter.password.value,
                onValueChange = loginPresenter::onPasswordChange,
                label = { Text("password") })
            Button(onClick = { loginPresenter.onLoginButtonClicked() }) {
                Text("Login")
            }
        }
    }
}