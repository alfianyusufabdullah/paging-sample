package alfianyusufabdullah.pagingsample

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource

class MainDataSource : PageKeyedDataSource<Int, String>() {

    private val state = MutableLiveData<Boolean>()

    override fun loadInitial(
        params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, String>
    ) {

        val data = Array(30) {
            val fakeId = (200..600).random()
            "$fakeId Username "
        }

        callback.onResult(
            data.toMutableList(),
            null,
            2
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, String>) {
        state.postValue(true)

        Thread.sleep(3000)
        
        val data = Array(30) {
            val fakeId = (200..600).random()
            "$fakeId Username "
        }

        callback.onResult(
            data.toMutableList(),
            params.key + 1
        )

        state.postValue(false)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, String>) {
    }

    fun getState() = state

}