package com.example.rickmorty.data.model

data class GetAllCharactersModelDto(
    val info: Info? = null,
    val results: List<Results>? = null
) {
    data class Info(
        val count: Int? = null,
        val pages: Int? = null,
        val next: String? = null,
        val prev: String? = null
    )

    data class Results(
        val id: Int? = null,
        val name: String? = null,
        val status: String? = null,
        val species: String? = null,
        val type: String? = null,
        val gender: String? = null,
        val origin: Origin? = null,
        val location: Location? = null,
        val image: String? = null,
        val episode: List<String>? = null,
        val url: String? = null,
        val created: String? = null
    ) {
        data class Origin(
            val name: String? = null,
            val url: String? = null
        )

        data class Location(
            val name: String? = null,
            val url: String? = null
        )
    }
}
