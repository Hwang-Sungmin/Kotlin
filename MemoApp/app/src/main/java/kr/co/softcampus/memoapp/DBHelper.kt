package kr.co.softcampus.memoapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper : SQLiteOpenHelper {

    // 생성자 (데이터 베이스 이름을 정해준다.)
    constructor(context: Context) : super(context, "Momo.db", null, 1)

    // 데이터 베이스 파일이 생성될 때 자동 호출되는 메서드
    // 테이블 생성
    override fun onCreate(p0: SQLiteDatabase?) {
        // 쿼리문
        val sql = """
            create table MemoTable
            (memo_idx integer primary key autoincrement,
            memo_subject text not null,
            memo_text text not null,
            memo_date date not null)
        """.trimIndent()

        p0?.execSQL(sql)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }




}