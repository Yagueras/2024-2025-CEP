package viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProjectsViewModel : ViewModel() {
    var titleCardValue = MutableLiveData<String>()
    private val projectDepth = arrayOf(0,1,2,3)
    var fragmentDepth = MutableLiveData<Int>()

    init {
        fragmentDepth.value = 0
    }

    fun addDepth(): Int{
        fragmentDepth.value = fragmentDepth.value!! + 1
        return fragmentDepth.value!!
    }

    fun removeDepth(): Int{
        fragmentDepth.value = fragmentDepth.value!! - 1
        return fragmentDepth.value!!
    }

    fun validDepth() {
        try {
            projectDepth[fragmentDepth.value!!]
        }catch (invalidDepth: ArrayIndexOutOfBoundsException){
            fragmentDepth.value = 0
        }
    }
}


