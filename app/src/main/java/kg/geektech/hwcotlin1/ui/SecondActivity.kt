package kg.geektech.hwcotlin1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kg.geektech.hwcotlin1.Myapp
import kg.geektech.hwcotlin1.R
import kg.geektech.hwcotlin1.databinding.ActivitySecondBinding
import kg.geektech.hwcotlin1.models.Story
import kg.geektech.hwcotlin1.objects.Constants.FIRST_ACTIVITY_KEY
import kg.geektech.hwcotlin1.objects.Constants.SECOND_ACTIVITY_KEY

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        sendData()
    }

    private fun sendData() = with(binding){
        btnSendTo.setOnClickListener{
            if (etSomeText.text.isNullOrEmpty()){
                Toast.makeText(this@SecondActivity, R.string.there_is_nothing,
                    Toast.LENGTH_LONG).show()
            }else {
                sendMessage(etSomeText.text.toString())
            }
        }

        btnThirdFragment.setOnClickListener{
            val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViews() {
        val extras:Bundle? = intent.extras
        if (extras != null) {
            binding.etSomeText.setText(extras.getString(FIRST_ACTIVITY_KEY).toString())
            //курсор помешает в конец строки
            binding.etSomeText.setSelection(binding.etSomeText.text.length)
        }
    }

    private fun sendMessage(message: String) {
        val data = Intent()
        data.putExtra(SECOND_ACTIVITY_KEY, message)
        setResult(RESULT_OK, data)
        saveForHistory(message)
        finish()
    }

    override fun onBackPressed() {
        sendMessage(binding.etSomeText.text.toString())
        super.onBackPressed()
    }

    private fun saveForHistory(message: String) {
        val story = Story(
            data = System.currentTimeMillis(),
            text = message
        )
        Myapp.mydatabase!!.myDao().InsertStory(story)
    }
}