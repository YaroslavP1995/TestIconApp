package play.top20play.testiconapp.api.repo

import play.top20play.testiconapp.api.RetrofitService
import play.top20play.testiconapp.data.BaseData
import retrofit2.Response

class MainRepository constructor(private val retrofitService: RetrofitService) : MainRepoImpl {

    override suspend fun search(tag: String): Response<BaseData> {
        return retrofitService.updateCampaign(tag)
    }
}