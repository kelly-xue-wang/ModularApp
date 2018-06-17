package com.example.kellywang.myapplication

import com.example.kellywang.commonlibrary.router.BaseApplication
import com.example.kellywang.commonlibrary.router.context


class AppApplication : BaseApplication() {
  override fun onCreate() {
    super.onCreate()
    context = this
    lifeCycleManager = ModuleLifeCycleManager(this)
    lifeCycleManager!!.onCreate()
  }

  override fun onTerminate() {
    super.onTerminate()
    lifeCycleManager!!.onDestroy()
  }
}