package kg.geektech.hwcotlin1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.geektech.hwcotlin1.models.Story

@Database(entities = [Story::class], version = 1)
abstract class MyDataBase: RoomDatabase(){
    abstract fun myDao() : MyDao

}