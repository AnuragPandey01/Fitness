package xyz.droidev.fitness.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import xyz.droidev.fitness.data.api.RetrofitInstance
import xyz.droidev.fitness.data.model.HomeResponse

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private var _response : MutableLiveData<Response<HomeResponse>> = MutableLiveData()
    val response: LiveData<Response<HomeResponse>>
        get() = _response

    fun loadData(){
        viewModelScope.launch {
            try {
                _response.value = RetrofitInstance.api.getData()
            }catch (e:Exception){
                _err.value = e.message
            }

        }
    }

    private var _err : MutableLiveData<String> = MutableLiveData()
    val err: LiveData<String>
        get() = _err

}