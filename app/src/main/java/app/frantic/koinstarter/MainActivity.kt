package app.frantic.koinstarter

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import app.frantic.koinstarter.adapter.FlowerAdapter
import app.frantic.koinstarter.databinding.ActivityMainBinding
import app.frantic.koinstarter.model.Flower
import app.frantic.koinstarter.model.FlowerRepository
import app.frantic.koinstarter.view_model.FlowerFactory
import app.frantic.koinstarter.view_model.FlowerViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

//    val factory = FlowerFactory(FlowerRepository.newInstance())

    /*val viewModel by lazy {
        ViewModelProviders.of(this,factory).get(FlowerViewModel::class.java)
    }*/

    val viewModel : FlowerViewModel by currentScope.inject()

    lateinit var binding:ActivityMainBinding

    lateinit var adapter: FlowerAdapter
    var flowerList:ArrayList<Flower> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.isLoading = true
        setSupportActionBar(toolbar)

        adapter = FlowerAdapter(flowerList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        val changeObserver = Observer<List<Flower>> { value ->
            value?.let {
                flowerList.addAll(it)
                adapter.notifyDataSetChanged()
                binding.isLoading = false
            }
        }
        viewModel.getFlowers().observe(this,changeObserver)

        binding.executePendingBindings()

    }

}
