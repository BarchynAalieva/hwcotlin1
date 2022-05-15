package kg.geektech.hwcotlin1.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Story(
    @PrimaryKey
    val data: Long,
    val text: String?
)
