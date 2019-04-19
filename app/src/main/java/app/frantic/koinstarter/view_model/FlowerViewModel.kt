package app.frantic.koinstarter.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.frantic.koinstarter.model.Flower
import app.frantic.koinstarter.model.FlowerRepository

class FlowerViewModel(val repository: FlowerRepository): ViewModel() {

    private val flowers : MutableLiveData<List<Flower>> by lazy {
        MutableLiveData<List<Flower>>().also{
            loadFlowers()
        }
    }
    fun getFlowers(): LiveData<List<Flower>>{
        return loadFlowers()
    }

    private fun loadFlowers():LiveData<List<Flower>>{
        return repository.getFlowers()
    }

}