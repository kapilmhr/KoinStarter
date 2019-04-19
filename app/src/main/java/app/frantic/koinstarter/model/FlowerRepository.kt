package app.frantic.koinstarter.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.frantic.koinstarter.network.RestApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FlowerRepository {

    fun getFlowers():LiveData<List<Flower>>{
        val data = MutableLiveData<List<Flower>>()
        GlobalScope.launch {
            val flowers = RestApi.invoke().getFlowers().await()
            if (flowers.isSuccessful){
                data.postValue(flowers.body()!!)
            }else{
                data.value = null
            }
        }
        return data
    }
}