package fi.metropolia.retrofitapp

data class WikiResponse(
    val batchcomplete: String,
    val `continue`: Continue,
    val query: Query
)