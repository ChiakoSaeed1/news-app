package com.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.ActivityMainBinding
import com.utils.MY_API_KEY
import com.utils.MyResponse
import com.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //Binding
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    //DI
    @Inject
    lateinit var adapterNews: AdapterNews

    private lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this)[NewsViewModel::class.java]
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initialize binding
        cacheData()

        binding.apply {
            viewModel.newsData.observe(this@MainActivity){response->
                when(response){
                    is MyResponse.Loading->{
                        showLoading(true)
                    }
                    is MyResponse.Success->{
                        showLoading(false)
                        response.data?.articles.let {data->
                            if (data!!.isNotEmpty()) {
                                adapterNews.setData(data)

                            }
                        }
                    }
                    is MyResponse.Error->{
                        showLoading(false)
                    }
                }
            }
        }

    }

    fun showLoading(isShown:Boolean) {
        binding.apply {
            if (isShown) {
                load.visibility = View.VISIBLE
                recNews.visibility = View.GONE
            }else{
                recNews.visibility = View.VISIBLE
                load.visibility = View.GONE
            }
        }
    }

    private fun cacheData() {
        binding.apply {
            recNews.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = adapterNews
            }
            viewModel.readCache.observe(this@MainActivity){data->
                if (data.isNotEmpty()){
                    showLoading(false)
                    adapterNews.setData(data[0].response.articles!!)
                }else{
                    viewModel.loadNews(viewModel.newsApi())
                }

            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}