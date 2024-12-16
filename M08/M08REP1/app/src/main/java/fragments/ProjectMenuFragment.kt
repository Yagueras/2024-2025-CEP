package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.m08_rep1.R

class ProjectMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_project_menu, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonProjectOverview = view.findViewById<Button>(R.id.button_project_overview)
        val projectProgressChart = view.findViewById<LinearLayout>(R.id.menu_project_progress_chart)
        val projectTimeUsed = view.findViewById<LinearLayout>(R.id.menu_time_used_chart)
    }
}