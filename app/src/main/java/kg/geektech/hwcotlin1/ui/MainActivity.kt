package kg.geektech.hwcotlin1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import kg.geektech.hwcotlin1.Myapp
import kg.geektech.hwcotlin1.R
import kg.geektech.hwcotlin1.databinding.ActivityMainBinding
import kg.geektech.hwcotlin1.models.Story
import kg.geektech.hwcotlin1.objects.Constants.FIRST_ACTIVITY_KEY
import kg.geektech.hwcotlin1.objects.Constants.SECOND_ACTIVITY_KEY

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sendData()
    }

    private fun sendData() {
        binding.button.setOnClickListener {
            if (binding.edFirst.text.isNullOrEmpty()) {
                Toast.makeText(
                    this, getString(R.string.there_is_nothing),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                // скрывает клавиатуру чтобы не мешало каждый раз
                binding.edFirst.onEditorAction(EditorInfo.IME_ACTION_DONE)
                openSomeActivityForResult()
            }
        }
    }

    private fun openSomeActivityForResult() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(FIRST_ACTIVITY_KEY, binding.edFirst.text.toString())
        saveForHistory()
        resultLauncher.launch(intent)
    }

    private fun saveForHistory() {
        val story = Story(
            data = System.currentTimeMillis(),
            text = binding.edFirst.text.toString()
        )
        Myapp.mydatabase!!.myDao().InsertStory(story)

    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts
            .StartActivityForResult(), fun(result: ActivityResult) {
            if (result.resultCode == RESULT_OK) {
                val intent: Intent? = result.data
                val accessMessage = intent!!.getStringExtra(SECOND_ACTIVITY_KEY)
                binding.edFirst.setText(accessMessage.toString())
                //курсор помешает в конец строки
                binding.edFirst.setSelection(binding.edFirst.text.length)
            }
        }
    )
}