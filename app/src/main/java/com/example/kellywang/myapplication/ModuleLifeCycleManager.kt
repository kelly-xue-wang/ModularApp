package com.example.kellywang.myapplication

import android.app.Application
import com.example.kellywang.commonlibrary.router.BaseModuleLifeCycle
import com.example.kellywang.commonlibrary.router.BaseModuleLifeCycleManager
import com.example.kellywang.commonlibrary.router.IModuleConfig
import com.example.kellywang.commonlibrary.router.IModuleLifeCycle
import com.example.kellywang.commonlibrary.router.ModuleConfig
//import com.example.kellywang.modulea.SellModuleLifeCycle
import com.example.kellywang.modulea.SellModuleLifeCycle
import com.example.kellywang.moduleb.HMCModuleLifeCycle
import java.util.ArrayList

internal class ModuleLifeCycleManager(application: Application) : BaseModuleLifeCycleManager(application) {

  init {
    moduleLifeCycleList.add(HMCModuleLifeCycle(application))
    moduleLifeCycleList.add(SellModuleLifeCycle(application))
  }


}