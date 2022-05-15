package kg.geektech.hwcotlin1

import android.app.Application
import androidx.room.Room
import kg.geektech.hwcotlin1.data.MyDataBase

class Myapp : Application() {

    companion object{
        var mydatabase: MyDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        mydatabase = Room.databaseBuilder(
            applicationContext,
            MyDataBase::class.java,"Mydb"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}
