package my.app.cityforum.api


data class OutputLogin(
    val username: String,
    val password: String,
    val status: String,
    val MSG: String,
    val id: Int
)