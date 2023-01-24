package dev.srijith.sample.composenavigations.login

import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter

class LoginPresenter(private val navigatorPresenter: NavigatorPresenter) {
    fun onLoginButtonClicked() {
        navigatorPresenter.onLoginClicked()
    }

}