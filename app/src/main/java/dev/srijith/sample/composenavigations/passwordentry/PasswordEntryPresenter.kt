package dev.srijith.sample.composenavigations.passwordentry

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.bundleOf
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter

class PasswordEntryPresenter(private val navigatorPresenter: NavigatorPresenter) {
    val enableCheckboxes: MutableState<Boolean> = mutableStateOf(true)

    val passValueYesRadioButton: MutableState<Boolean> = mutableStateOf(false)
    val passValueNoRadioButton: MutableState<Boolean> = mutableStateOf(false)

    fun onPassValueYesRadioButtonClicked() {
        if (!passValueYesRadioButton.value) {
            passValueYesRadioButton.value = true
            passValueNoRadioButton.value = false
        }
    }

    fun onPassValueNoRadioButtonClicked() {
        if (!passValueNoRadioButton.value) {
            passValueNoRadioButton.value = true
            passValueYesRadioButton.value = false
        }
    }

    fun onSubmitClicked() {
        navigatorPresenter.onPasswordEntrySubmitClicked(
            if (passValueYesRadioButton.value) bundleOf(
                "passBack" to "PassBack Success"
            ) else null
        )
    }

}