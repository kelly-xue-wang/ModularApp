# ModularApp

Sync the submodules
```
   git submodule sync && git submodule update --init --recursive
```
## Modularization
Framework

![Framework](https://raw.githubusercontent.com/kelly-xue-wang/ModularApp/master/img/Router_Framework.jpeg)

App build.gradle
```gradle
    //Common
    implementation project(':sharedmodules:moduleBase')
    implementation project(':sharedmodules:moduleService')
    //modules
    implementation project(':sharedmodules:moduleHMC')
    implementation project(':sharedmodules:moduleSell')
    //optional
    implementation project(':sharedmodules:api:apilibbase')
    implementation project(':sharedmodules:api:apilibHMC')
    implementation project(':sharedmodules:api:apilibSell')
```
## moduleBase

Contains Router and other shared stuff across all business modules
## moduleService

if you want to get data from a business module, we need to define interface and implementation
 
```kotlin
interface ISellService {
  fun getSellerName(): String
}
```
```kotlin
class SellServiceImpl : ISellService {
  override fun getSellerName(): String {
    return "Hello My name is seller module"
  }

}
```
## Communication
Protocol Format: scheme://host/path

Start Activity 
```kotlin
 Router.open(context, "app://sell/activity")
```
Start Fragment
```kotlin
  val fragment = Router.getRouterFragment("app://sell/fragment")
  if (fragment != null) {
      val ft = fragmentManager?.beginTransaction()
      ft?.replace(R.id.container, fragment)?.addToBackStack("backstackname")?.commitAllowingStateLoss()
  }
```
Get value from module
```kotlin
 val sellService = InjectHelper.getInstance(ISellService::class.java)
 if(sellService != null) {
     Toast.makeText(context, sellService.getSellerName(), Toast.LENGTH_SHORT).show()
 }
```

## Library Module
### Setup router module
```kotlin
class SellModuleLifeCycle(application: Application) : BaseModuleLifeCycle(application) {


  override fun onCreate(config: IModuleConfig) {
  config.registerService(ISellService::class.java, SellServiceImpl::class.java)
  config.registerRouterActivity("app://sell/activity", SellActivity::class.java)
  config.registerFragmentRouter("app://sell/fragment", SellVerticalSelectionFragment::class.java)
  }
}
```

App side initialize modules
```kotlin
val moduleLifeCycleList: MutableList<IModuleLifeCycle>
   
class AppApplication : BaseApplication() {
  override fun onCreate() {
    super.onCreate()
    context = this
   moduleLifeCycleList.add(HMCModuleLifeCycle(application))
   moduleLifeCycleList.add(SellModuleLifeCycle(application))
  }
  
  override fun onTerminate() {
      super.onTerminate()
     for (moduleLifeCycle in moduleLifeCycleList) {
           moduleLifeCycle.onDestroy()
         }
    }
}

```

* Debug/run single module
add gradle.properties to each module library and set `isLibModule = true`

```gradle
if (isLibModule.toBoolean()) {
    apply plugin: 'com.android.library'    
} else {
    apply plugin: 'com.android.application'
}
```

## TODO
* Protocol Management
Protocol urls need to be managed properly
