package com.ahmed.login_jetpack_compose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmed.login_jetpack_compose.data.local.entity.UserDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(userDataEntity: UserDataEntity): Long

    @Query("select * from tbl_userdata where userId=:userId order by id desc")
    fun getUserDataByUserId(userId: String): Flow<List<UserDataEntity>>


    @Query("delete from tbl_userdata where id=:id")
    suspend fun deleteUserDataById(id: Int)


}

