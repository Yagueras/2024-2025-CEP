package viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProjectsViewModel: ViewModel() {
    val selectedProjectName = MutableLiveData<String>()
}

