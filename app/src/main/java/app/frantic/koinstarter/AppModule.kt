package app.frantic.koinstarter

import app.frantic.koinstarter.model.FlowerRepository
import app.frantic.koinstarter.view_model.FlowerViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module{

    single { FlowerRepository() }

    scope(named<MainActivity>()){
        scoped { FlowerViewModel(get()) }
    }

//    viewModel { FlowerViewModel(get()) }

}