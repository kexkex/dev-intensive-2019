package ru.skillbranch.devintensive


import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send) {
            val (phrase, color) = benderObj.listenAnswer(et_massege.text.toString().toLowerCase())
            messageEt.setText("")
            val (r,g,b) = color
            benderImage.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase
        }

    }

    lateinit var benderImage:ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt:EditText
    lateinit var sendBtn:ImageView

    lateinit var benderObj:Bender


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_massege
        sendBtn = iv_send

        var status = savedInstanceState?.getString("Status") ?: Bender.Status.NORMAL.name
        var question = savedInstanceState?.getString("Question") ?: Bender.Question.NAME.name

        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))
        val (r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)


        textTxt.text = benderObj.askQuestion()

        sendBtn.setOnClickListener(this)

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString("Status", benderObj.status.name)
        outState?.putString("Question", benderObj.question.name)
    }
}
