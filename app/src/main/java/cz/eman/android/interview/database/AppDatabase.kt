package cz.eman.android.interview.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.eman.android.interview.database.User.User
import cz.eman.android.interview.database.User.UserDao

@Database(
    entities = [
        User::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}