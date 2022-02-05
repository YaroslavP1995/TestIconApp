package play.top20play.testiconapp.api.repo

import play.top20play.testiconapp.data.BaseData
import retrofit2.Response

interface MainRepoImpl {
    suspend fun search(tag: String): Response<BaseData>
}