# KoinStarter
Koin is a dependency injection framework. Koin is purely written in Kotlin. This is a koin starter pack

Add dependency in project
```
implementation 'org.koin:koin-android-viewmodel:2.0.0-rc-2'
 ```
 
### create module class

```
val appModule = module{

//inject FlowerRepository class's instance which is made singleton
    single { FlowerRepository() }

// Inject Viewmodel in its scope
    scope(named<MainActivity>()){
        scoped { FlowerViewModel(get()) }
    }

//Inject View Model Directly
 viewModel { FlowerViewModel(get()) }

}
```

### Application
```
class MyApplication:Application(){

    override fun onCreate() {
        super.onCreate()

        // start Koin
        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}
```

### MainActivity
```
//If viewmodel is injected in its scope ,here MainActivity is its scope
val viewModel : FlowerViewModel by currentScope.inject()

//If directly viewmodel is injected
val viewModel : FlowerViewModel by viewModel()
```

