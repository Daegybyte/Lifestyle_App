package com.example.project

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // if there is a conflict, replace the old data
    suspend fun insert(user: User)

    @Query("DELETE FROM user_table") // delete all users
    suspend fun deleteAll()

    @Query("SELECT * FROM user_table ORDER BY id DESC limit 1") // get the most recent user
    fun getUser(): Flow<User>
}