package dev.srijith.sample.composenavigations

class UserRepository {
    fun fetchUserDetails(): User {
        return User("John", "Doe", "johndoe@example.com")
    }
}

class User(
    val firstName: String,
    val lastName: String,
    val emailId: String
)