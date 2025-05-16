package cz.eman.android.interview.repository

class Repository {
    private fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun getRandomName(length: Int) = getRandomString(
        length)
}