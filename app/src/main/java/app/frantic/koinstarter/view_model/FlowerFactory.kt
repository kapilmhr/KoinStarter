package app.frantic.koinstarter.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.frantic.koinstarter.model.FlowerRepository

class FlowerFactory(val repository: FlowerRepository)
    : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FlowerViewModel(repository) as T
    }
}
