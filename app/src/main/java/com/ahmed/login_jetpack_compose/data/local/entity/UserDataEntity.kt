package com.ahmed.login_jetpack_compose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tbl_userdata",
    foreignKeys = [ForeignKey(
        entity = LoginEntity::class,
        parentColumns = ["userId"],
        childColumns = ["userId"],
        onDelete = CASCADE
    )],
    indices = [Index(value = ["userId"])]
)
data class UserDataEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo("userId") val userId: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("occupation") val occupation: String,
    @ColumnInfo("age") val age: Int,
    @ColumnInfo("gender") val gender: String,
)
