package play.top20play.testiconapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import play.top20play.testiconapp.api.RetrofitService
import play.top20play.testiconapp.data.NeededData
import play.top20play.testiconapp.databinding.ActivityMainBinding
import play.top20play.testiconapp.fragment.ListIconsFragment
import play.top20play.testiconapp.api.repo.MainRepository

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    private val listData: ArrayList<NeededData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(this, ViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        initUi()
        initObserver()
    }

    private fun initUi(){
        binding.searchBtn.setOnClickListener {
            viewModel.getAllPicture(getSearchText())
        }
    }

    private fun initObserver() {
        viewModel.pictureList.observe(this, {
            it.let {
                for (data in it.response) {
                    try {
                        if (data.photos.isNotEmpty() && data.photos[0].original_size.url.isNotEmpty()) {
                            listData.add(
                                NeededData(
                                    data.blog.name,
                                    data.photos[0].original_size.url
                                )
                            )
                        }
                    }catch (ex: NullPointerException){
                        ex.printStackTrace()
                    }
                }
            }
            if (listData.isNotEmpty()) {
                initIconsFragment(listData)
            }
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, {
            if (it) {
                //TODO
            } else {
                //TODO
//                if (listData.isNotEmpty()) {
//                    initIconsFragment(listData)
//                }
            }
        })
    }


    private fun initIconsFragment(list: ArrayList<NeededData>) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.rootView, ListIconsFragment.newInstance(list))
        transaction.addToBackStack("MainActivity")
        transaction.commit()
    }

    private fun getSearchText() = binding.editText?.text.toString()

}