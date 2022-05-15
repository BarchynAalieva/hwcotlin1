package kg.geektech.hwcotlin1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kg.geektech.hwcotlin1.Myapp
import kg.geektech.hwcotlin1.adapter.ListAdapter
import kg.geektech.hwcotlin1.databinding.ActivityThirdBinding
import kg.geektech.hwcotlin1.models.Story

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getAndSetData()
    }
    private fun getAndSetData() {
        linearLayoutManager = LinearLayoutManager(this)
        binding.recView.layoutManager = linearLayoutManager
        val list = Myapp.mydatabase!!.myDao().getSortedStory()
        binding.recView.adapter= ListAdapter(list as ArrayList<Story>)
    }
}