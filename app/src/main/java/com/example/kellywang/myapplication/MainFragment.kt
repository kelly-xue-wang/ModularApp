package com.example.kellywang.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kellywang.commonlibrary.router.InjectHelper
import com.example.kellywang.commonlibrary.router.Router
import com.example.kellywang.moduleservice.ISellService
import kotlinx.android.synthetic.main.fragment_main.btnGetValue
import kotlinx.android.synthetic.main.fragment_main.btnLaunchSellActivity
import kotlinx.android.synthetic.main.fragment_main.btnLaunchSellFragment

class MainFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_main, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    btnLaunchSellActivity.setOnClickListener {
      Router.open(this.context, "app://sell/activity")


    }

    btnLaunchSellFragment.setOnClickListener {
      val fragment = Router.getRouterFragment("app://sell/fragment")
      if (fragment != null) {
        val ft = fragmentManager?.beginTransaction()
        ft?.replace(R.id.container, fragment)?.addToBackStack("app://sell/fragment")?.commitAllowingStateLoss()
      }
    }

    btnGetValue.setOnClickListener {
      //use Dagger later
      val sellService = InjectHelper.getInstance(ISellService::class.java)
      if(sellService != null) {
        Toast.makeText(context, sellService.getSellerName(), Toast.LENGTH_SHORT).show()
      }

    }
  }
}