package alfianyusufabdullah.pagingsample

import androidx.paging.DataSource


class MainDataSourceFactory: DataSource.Factory<Int, String>() {

    private val mainDataSource = MainDataSource()

    override fun create(): DataSource<Int, String> {
        return mainDataSource
    }

    fun getState() = mainDataSource.getState()
}