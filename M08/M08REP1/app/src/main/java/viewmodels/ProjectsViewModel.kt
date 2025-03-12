package viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProjectsViewModel : ViewModel() {
    private val newTitleCardValue = MutableLiveData<String>()
    val titleCardValue: LiveData<String> get() = newTitleCardValue
    private val projectDepth = arrayOf(0,1,2,3)
    private val newFragmentDepth = MutableLiveData<Int>().apply { value = 0 }
    val fragmentDepth: LiveData<Int> get() = newFragmentDepth

    fun setTitleCardValue(newValue: String) {
        newTitleCardValue.value = newValue
    }

    fun addDepth(){
        val current = newFragmentDepth.value ?: 0
        newFragmentDepth.value = current + 1
    }

    fun removeDepth(){
        val current = newFragmentDepth.value ?: 0
        newFragmentDepth.value = (current - 1).coerceAtLeast(0)
    }

    fun checkIfNull(){
        try {
            fragmentDepth.value!! + 0
        }catch (isNull: NullPointerException){
            newFragmentDepth.value = 0
        }
    }

    fun checkValidDepth() {
        try {
            projectDepth[fragmentDepth.value!!]
        }catch (invalidDepth: ArrayIndexOutOfBoundsException){
            newFragmentDepth.value = 0
        }
    }
}