package com.ahmed.login_jetpack_compose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmed.login_jetpack_compose.data.local.entity.LoginEntity

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertLogin(loginEntity: LoginEntity)

    @Query("Select * from tbl_login where email=:email and password=:password Limit 1")
    suspend fun getLoginByEmailAndPassword(email: String, password: String): LoginEntity?

    @Query("Select * from tbl_login where email=:email limit 1")
    suspend fun getLoginByEmail(email: String): LoginEntity?

    @Query("Update tbl_login set password =:newPassword where email=:email")
    suspend fun updatePassword(email: String,newPassword: String): Int

    @Query("Select count(*) from tbl_login where email=:email")
    suspend fun isEmailExists(email: String): Int
}