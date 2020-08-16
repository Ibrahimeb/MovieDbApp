package com.ibrahim.moviedbapp.app.network

import android.util.Log
import com.ibrahim.moviedbapp.commons.BaseContract
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class CallbackHandlingObserver<T>(private val presenter: BaseContract.ServicePresenter, private val interactor: String) : DisposableObserver<T>() {

    //private val TAG = CallbackHandlingObserver::class.simpleName

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(ex: Throwable) {
        Log.i("Info", "onError: ex cause-->" + ex.cause)
        Log.i("Info", "onError: ex message-->" + ex.message)
        if (ex is HttpException) {
            val errorCode = ex.response()?.code()
            Log.i("Info", "onError: ex-->" + ex.message())
            if (errorCode != null) {
                handleErrorCode(errorCode, interactor)
            }
        } else if (ex is SocketTimeoutException) {
            presenter.onTimeoutError(interactor)

        } else if (ex is IOException) {
            presenter.onNetworkError(interactor)

        } else {
            presenter.onUnknownError(ex.message!!, interactor)
        }


    }


    private fun handleErrorCode(errorCode: Int, interactor: String) {

        Log.i("Info", "handleErrorCode: codigo error $errorCode")

        if (errorCode == 500) {
            presenter.onServerError(interactor)
        } else if (errorCode in 400..499)
            presenter.onBadRequestError(interactor, errorCode)

    }

    override fun onComplete() {
        //DO NOTHING
    }


    protected abstract fun onSuccess(data: T)
}