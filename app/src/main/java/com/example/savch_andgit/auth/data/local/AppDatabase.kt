package com.example.savch_andgit.auth.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.savch_andgit.music.data.local.TrackDao
import com.example.savch_andgit.music.data.local.TrackEntity

@Database(entities = [UserEntity::class, TrackEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun trackDao(): TrackDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS tracks (id INTEGER NOT NULL PRIMARY KEY, name TEXT NOT NULL, artist TEXT NOT NULL, artworkUrl TEXT, previewUrl TEXT, trackUrl TEXT)"
                )
            }
        }
    }
}
