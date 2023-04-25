package kr.co.softcampus.memoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.graphics.createBitmap
import kr.co.softcampus.memoapp.databinding.ActivityMemoReadBinding

class MemoReadActivity : AppCompatActivity() {

    lateinit var binding: ActivityMemoReadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMemoReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.memoReadToolbar)
        title = "메모 읽기"

        // 툴바에 홈버튼
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onResume() {
        super.onResume()

        // 데이터 베이스 값 가져오기
        val helper = DBHelper(this)

        val sql = """
            select memo_subject, memo_date, memo_text
            from MemoTable
            where memo_idx = ?
        """.trimIndent()

        // 글 번호 추철
        val memo_idx = intent.getIntExtra("memo_idx", 0)

        // 쿼리 실행
        val args = arrayOf(memo_idx.toString())
        val c1 = helper.writableDatabase.rawQuery(sql, args)
        c1.moveToNext()

        // 글 데이터를 추출
        val idx1 = c1.getColumnIndex("memo_subject")
        val idx2 = c1.getColumnIndex("memo_date")
        val idx3 = c1.getColumnIndex("memo_text")

        val memo_subject = c1.getString(idx1)
        val momo_date = c1.getString(idx2)
        val memo_text = c1.getString(idx3)

        helper.writableDatabase.close()
        //Log.d("memo_app", memo_subject)
        //Log.d("memo_app", momo_date)
        //Log.d("memo_app", memo_text)

        binding.memoReadSubject.text = "제목 : $memo_subject"
        binding.memoReadDate.text = "작성날짜 : $momo_date"
        binding.memoReadText.text = memo_text

    }

    // 툴바 메뉴 노출
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.read_menu, menu)
        return true
    }


    // 홈 버튼 누르기 할 때 뒤로가기 기능 추가
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            // 메뉴 수정
            R.id.read_modify -> {

            }
            // 메뉴 삭제
            R.id.read_delete -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }


}