package dev.srijith.sample.composenavigations.passwordentry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun PasswordEntry(passwordEntryDependencyComponent: PasswordEntryDependencyComponent) {
    val presenter = passwordEntryDependencyComponent.passwordEntryPresenter
//    LaunchedEffect(Unit) {
//        presenter.onPasswordEntryDisplayed()
//    }
    Column {
        Text(text = "Password Entry", color = Color.Red)
        Row {
            Checkbox(
                checked = presenter.configCheckCheckBoxState.value,
                onCheckedChange = presenter::onConfigCheckCheckboxChange,
                enabled = presenter.enableCheckboxes.value
            )
            Text(text = "Config Check")
        }
    }
}