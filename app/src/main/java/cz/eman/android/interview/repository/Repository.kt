package cz.eman.android.interview.repository

import cz.eman.android.interview.database.AppDatabase
import cz.eman.android.interview.database.User.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class Repository(
    private val database: AppDatabase
) {

    val names = runBlocking(Dispatchers.IO) {  database.userDao().getAll() }

    suspend fun saveName(jmeno: String, prijmeni: String) {
        if (names.any { it.lastName == prijmeni }) {
            database.userDao().update(
                User(database.userDao().findByName(jmeno, prijmeni).uid, jmeno, prijmeni))
        } else {
            database.userDao().insertAll(User(0, jmeno, prijmeni))
        }
    }
}