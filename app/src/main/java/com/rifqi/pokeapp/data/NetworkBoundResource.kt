package com.rifqi.pokeapp.data

import androidx.annotation.MainThread
import com.rifqi.pokeapp.domain.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

@FlowPreview
@ExperimentalCoroutinesApi
abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading)
        when (val apiResponse = fetchFromNetwork().first()) {
            is ApiResponse.Success -> {
//                saveNetworkResult(processResponse(apiResponse))
                emit(Resource.Success(loadFromApi(apiResponse.data)))
            }
            is ApiResponse.Error -> {
                onFetchFailed()
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromApi(data: RequestType): ResultType

//    @WorkerThread
//    protected open fun processResponse(response: ApiResponse.Success<RequestType>) = response.data
//
//    @WorkerThread
//    protected abstract suspend fun saveNetworkResult(item: RequestType)
//
//    @MainThread
//    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result

}