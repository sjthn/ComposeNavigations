package dev.srijith.sample.composenavigations.passwordentry

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class PasswordEntryPresenter {
    val enableCheckboxes: MutableState<Boolean> = mutableStateOf(true)
    val configCheckCheckBoxState: MutableState<Boolean> = mutableStateOf(false)
    fun onPasswordEntryDisplayed() {
        enableCheckboxes.value = true
    }

    fun onConfigCheckCheckboxChange(newState: Boolean) {
        configCheckCheckBoxState.value = newState
    }
}