package kr.co.softcampus.memoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import kr.co.softcampus.memoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        print("[hsm511] MainActivity -> onCreate()")

        // 스플래쉬 화면 이후로 보여질 화면의 테마 설정
        SystemClock.sleep(3000)
        setTheme(R.style.Theme_MemoApp)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val helper = DBHelper(this)
        helper.writableDatabase.close()

    }

    override fun onResume() {
        super.onResume()
        print("[hsm511] MainActivity -> onResume()")
    }

    override fun onPause() {
        super.onPause()
        print("[hsm511] MainActivity -> onPause()")
    }


    override fun onDestroy() {
        super.onDestroy()
        print("[hsm511] MainActivity -> onDestroy()")
    }
}