package dev.srijith.sample.composenavigations.passwordentry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun PasswordEntry(passwordEntryDependencyComponent: PasswordEntryDependencyComponent) {
    val presenter = passwordEntryDependencyComponent.passwordEntryPresenter
    Column {
        Text(text = "Password Entry", color = Color.Red)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Pass Value Back:")
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = presenter.passValueYesRadioButton.value,
                    onClick = presenter::onPassValueYesRadioButtonClicked,
                    enabled = presenter.enableCheckboxes.value
                )
                Text(text = "Yes")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = presenter.passValueNoRadioButton.value,
                    onClick = presenter::onPassValueNoRadioButtonClicked,
                    enabled = presenter.enableCheckboxes.value
                )
                Text(text = "No")
            }
        }
        Button(onClick = presenter::onSubmitClicked) {
            Text(text = "Submit")
        }
    }
}