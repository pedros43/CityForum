package my.app.cityforum.api

data class Problemas(
    val id: Int,
    val latitude: Float,
    val longitude: Float,
    val titulo: String,
    val descr: String,
    val img: String,
    val user_id: Int
)
