package kr.co.softcampus.memoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.softcampus.memoapp.databinding.ActivityMainBinding
import kr.co.softcampus.memoapp.databinding.MainRecylerRowBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    //제목을 담을 ArrayList
    val subject_list = ArrayList<String>()

    // 작성 날짜를 담을 ArrayList
    val date_list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("TAG", "MainActivity -> onCreate()")

        // 스플래쉬 화면 이후로 보여질 화면의 테마 설정
        SystemClock.sleep(1000)
        setTheme(R.style.Theme_MemoApp)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴 바 설정
        setSupportActionBar(binding.mainToolbar)
        title = "메모앱"

        // 리사이클러뷰 세팅
        val main_recycler_adapter = MainRecyclerAdapter()
        binding.mainRecycler.adapter = main_recycler_adapter

        binding.mainRecycler.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
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
        Log.d("TAG", "MainActivity -> onResume()")

        // 리스트를 비워준다.
        subject_list.clear()
        date_list.clear()

        // 데이터 베이스 오픈
        val helper = DBHelper(this)

        // 쿼리문
        val sql = """
            select memo_subject, memo_date
            from MemoTable
            order by memo_idx desc
        """.trimIndent()


        val c1 = helper.writableDatabase.rawQuery(sql, null)

        while (c1.moveToNext()) {
            // 컬럼 index를 가져온다
            val idx1 = c1.getColumnIndex("memo_subject")
            val idx2 = c1.getColumnIndex("memo_date")

            // 데이터를 가져온다
            val memo_subject = c1.getString(idx1)
            val memo_date = c1.getString(idx2)

            subject_list.add(memo_subject)
            date_list.add(memo_date)
            //Log.d("memo_app", memo_subject)
            //Log.d("memo_app", memo_date)
            //Log.d("memo_app", "------------------------------------")

            // 리사이클 뷰에게 갱신하라고 명령한다.
            binding.mainRecycler.adapter?.notifyDataSetChanged()
        }
    }

    // RecyclerView의 어댑터
    inner class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolderClass>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val mainRecyclerBinding = MainRecylerRowBinding.inflate(layoutInflater)
            val holder = ViewHolderClass(mainRecyclerBinding)
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowMemoSubject.text = subject_list[position]
            holder.rowMemoDate.text = date_list[position]
        }

        override fun getItemCount(): Int {
            return subject_list.size
        }

        // HolderClass
        inner class ViewHolderClass(mainRecyclerBinding: MainRecylerRowBinding) :
            RecyclerView.ViewHolder(mainRecyclerBinding.root) {
            // View의 주소값을 담는다.
            val rowMemoSubject = mainRecyclerBinding.memoSubject
            val rowMemoDate = mainRecyclerBinding.memoDate
        }

    }


    override fun onPause() {
        super.onPause()
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}

