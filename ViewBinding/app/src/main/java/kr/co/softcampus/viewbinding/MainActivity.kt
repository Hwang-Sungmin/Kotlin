package kr.co.softcampus.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.softcampus.viewbinding.databinding.ActivityMainBinding
import kr.co.softcampus.viewbinding.databinding.ActivitySecondBinding

class MainActivity : AppCompatActivity() {

    // Binding 객체를 담을 변수
    lateinit var vinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding 객체를 추출
        vinding = ActivitySecondBinding.inflate(layoutInflater)
        
        // 화면을 세팅
        setContentView(vinding.root)

        vinding.button2.setOnClickListener{
            vinding.textView2.text = "황성민"
            vinding.button2.text="여기를 눌러봐라잉~"
            
        }
    }
}