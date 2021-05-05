package my.app.cityforum.api

data class Problemas(
    val id: Int,
    val latitude: Float,
    val longitude: Float,
    val tipo: String,
    val descricao: String,
    val imagem: String,
    val utilizador_id: Int
)
