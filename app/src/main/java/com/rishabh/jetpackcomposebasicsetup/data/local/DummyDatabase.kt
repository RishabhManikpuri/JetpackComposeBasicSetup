package com.rishabh.jetpackcomposebasicsetup.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DummyEntity::class],
    version = 1
)
abstract class DummyDatabase: RoomDatabase() {

    abstract val dao: DummyDao

}