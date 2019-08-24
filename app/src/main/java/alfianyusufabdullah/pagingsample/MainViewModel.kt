package alfianyusufabdullah.pagingsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import java.util.concurrent.Executors

class MainViewModel : ViewModel() {

    private var data: LiveData<PagedList<String>>
    private var state = MutableLiveData<Boolean>()

    init {
        val mainDataSourceFactory = MainDataSourceFactory()

        val configBuilder = PagedList.Config.Builder()
        val config = configBuilder
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(1)
            .setPrefetchDistance(10)
            .setPageSize(10)
            .build()

        val pagedListBuilder = LivePagedListBuilder<Int, String>(mainDataSourceFactory, config)

        data = pagedListBuilder.setFetchExecutor(Executors.newFixedThreadPool(5)).build()
        state = mainDataSourceFactory.getState()
    }

    fun getData() = data

    fun getState() = state
}