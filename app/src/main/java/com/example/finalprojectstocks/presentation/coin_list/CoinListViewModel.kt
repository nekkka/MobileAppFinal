package com.example.finalprojectstocks.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectstocks.data.network.dto.coinList.toCoin
import com.example.finalprojectstocks.domain.models.Coin
import com.example.finalprojectstocks.domain.repository.CoinRepository
import com.example.finalprojectstocks.presentation.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val coinRepository: CoinRepository
): ViewModel() {
    private var _coinList = MutableStateFlow<UIState<List<Coin>>>(UIState.Loading())
    public val coinList = _coinList

    fun getCoinList() {
        viewModelScope.launch(Dispatchers.IO) {
            _coinList.emit(UIState.Loading())
            try{
                _coinList.emit(UIState.Success(data = coinRepository.getAllCoins().map {
                    it.toCoin()
                }))
            }catch (e: Exception){
                _coinList.emit(UIState.Error(error = e.message ?: "Unknown error"))
            }

        }
    }
}