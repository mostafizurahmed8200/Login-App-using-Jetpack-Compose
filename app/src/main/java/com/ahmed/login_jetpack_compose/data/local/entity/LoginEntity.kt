package com.ahmed.login_jetpack_compose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_login")
data class LoginEntity(
    @PrimaryKey() @ColumnInfo(name = "userId") val userId: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String
)