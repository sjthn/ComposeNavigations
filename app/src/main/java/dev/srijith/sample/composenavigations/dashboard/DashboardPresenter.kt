package dev.srijith.sample.composenavigations.dashboard

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dev.srijith.sample.composenavigations.UserRepository
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter

class DashboardPresenter(
    private val userRepository: UserRepository,
    private val navigatorPresenter: NavigatorPresenter
) {
    fun onNicknameChanged(value: String) {
        nickname.value = value
    }

    val nickname: MutableState<String> = mutableStateOf("")
    val userDetails: MutableState<UserDetails?> = mutableStateOf(null)

    init {
        val user = userRepository.fetchUserDetails()
        userDetails.value = UserDetails(user.firstName, user.lastName, user.emailId)
    }

    fun onNewEntryClicked() {
        navigatorPresenter.onDashboardItemClicked()
    }
}

data class UserDetails(
    val firstName: String,
    val lastName: String,
    val email: String,
)