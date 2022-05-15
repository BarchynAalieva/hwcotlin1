package kg.geektech.hwcotlin1.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kg.geektech.hwcotlin1.models.Story

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertStory(story: Story)

    @Query("SELECT * From story")
    fun getAllStory(): List<Story>

    @Query("SELECT * From story ORDER BY data DESC")
    fun getSortedStory(): List<Story>
}