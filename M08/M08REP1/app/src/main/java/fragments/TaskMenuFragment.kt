package fragments

import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.m08_rep1.R
import dataClasses.Status
import dataClasses.Task

class TaskMenuFragment(val selectedTask: Task, val statusList: Array<Status>) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_menu, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentStatus = view.findViewById<Spinner>(R.id.project_status_picker)
        val timeUsedDisplay = view.findViewById<TextView>(R.id.time_used_display)
        val timeRequiredDisplay = view.findViewById<TextView>(R.id.time_required_display)

        val currentStatusAdapter:ArrayAdapter<String> = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, getStatusNames(statusList))
        currentStatus.adapter = currentStatusAdapter

        currentStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }
        }

        currentStatus.setSelection(statusList[selectedTask.status].statusID)
        timeUsedDisplay.text = selectedTask.timeUsed.toString()
        timeRequiredDisplay.text = selectedTask.requiredTime.toString()
    }

    private fun getStatusNames(statusList: Array<Status>): ArrayList<String> {
        val statusNameList: ArrayList<String> = arrayListOf()
        var statusName: String

        for (status in statusList){
            statusName = status.statusName
            statusNameList.add(statusName)
        }
        return statusNameList
    }

}