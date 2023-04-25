package kr.co.softcampus.memoapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import kr.co.softcampus.memoapp.databinding.ActivityMemoModifyBinding

class MemoModifyActivity : AppCompatActivity() {

    lateinit var binding: ActivityMemoModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMemoModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.memoModifyToolbar)
        title = "메모 수정"

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val helper = DBHelper(this)

        val sql = """
            select memo_subject, memo_text
            from MemoTable
            where memo_idx = ?
        """.trimIndent()

        val memo_idx = intent.getIntExtra("memo_idx", 0)

        val args = arrayOf(memo_idx.toString())
        val c1 = helper.writableDatabase.rawQuery(sql, args)
        c1.moveToNext()

        val idx1 = c1.getColumnIndex("memo_subject")
        val idx2 = c1.getColumnIndex("memo_text")

        val memo_subject = c1.getString(idx1)
        val memo_text = c1.getString(idx2)

        helper.writableDatabase.close()

        //Log.d("memo_app", memo_subject)
        //Log.d("memo_app", memo_text)

        binding.memoModifySubject.setText(memo_subject)
        binding.memoModifyText.setText(memo_text)

        Thread {
            SystemClock.sleep(500)

            runOnUiThread {
                binding.memoModifySubject.requestFocus()
                
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(binding.memoModifySubject, InputMethodManager.SHOW_IMPLICIT)
            }
        }.start()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}