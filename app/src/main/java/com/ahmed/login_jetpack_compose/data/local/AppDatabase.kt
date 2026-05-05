package com.ahmed.login_jetpack_compose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmed.login_jetpack_compose.data.local.dao.LoginDao
import com.ahmed.login_jetpack_compose.data.local.dao.UserDataDao
import com.ahmed.login_jetpack_compose.data.local.entity.LoginEntity
import com.ahmed.login_jetpack_compose.data.local.entity.UserDataEntity


@Database(
    entities = [LoginEntity::class, UserDataEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao
    abstract fun userDataDao(): UserDataDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }
}