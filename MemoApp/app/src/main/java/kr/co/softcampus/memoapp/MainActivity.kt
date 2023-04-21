package kr.co.softcampus.memoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
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
        
        // 툴 바 설정
        setSupportActionBar(binding.mainToolbar)
        title = "메모앱"
        
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            // 추가버튼
            R.id.main_menu_add -> {
                val memoIntent = Intent(this, MemoAddActivity::class.java)
                startActivity(memoIntent)
            }
        }

        return super.onOptionsItemSelected(item)
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

