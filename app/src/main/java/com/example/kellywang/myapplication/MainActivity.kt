package com.example.kellywang.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kellywang.modulea.SellVerticalSelectionFragment


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val ft = supportFragmentManager.beginTransaction()
    ft.replace(R.id.container, MainFragment()).commitAllowingStateLoss()

  }
}
