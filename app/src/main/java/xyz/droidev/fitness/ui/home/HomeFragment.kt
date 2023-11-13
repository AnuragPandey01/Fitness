package xyz.droidev.fitness.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import xyz.droidev.fitness.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by lazy{
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.loadData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.response.observe(viewLifecycleOwner ){res->
            if(!res.isSuccessful || res.body()?.success == false){
                binding.noInternetText.visibility = View.VISIBLE
                res.body()?.message?.let {
                    binding.noInternetText.text = it
                }
            }else{

                binding.noInternetText.visibility = View.GONE
                res.body()?.data?.let {

                    //section 1
                    binding.activePlanName.text = it.section_1.plan_name
                    binding.progressBar.progress = it.section_1.progress.dropLast(1).toInt()

                    //section 2
                    binding.accuracy.text = it.section_2.accuracy
                    binding.caloriesBurn.text = it.section_2.calories_burned.toString()
                    binding.workoutDuration.text = it.section_2.workout_duration
                    binding.reps.text = it.section_2.reps.toString()

                    //section 3
                    binding.plan1Name.text = it.section_3.plan_1.plan_name
                    binding.plan1Difficulty.text = it.section_3.plan_1.difficulty
                    binding.plan2Name.text = it.section_3.plan_2.plan_name
                    binding.plan2Difficulty.text = it.section_3.plan_2.difficulty

                    //section 4
                    binding.category1Name.text = it.section_4.category_1.category_name
                    binding.category1No.text = it.section_4.category_1.no_of_exercises
                    binding.category2Name.text = it.section_4.category_2.category_name
                    binding.category2No.text = it.section_4.category_2.no_of_exercises
                }
            }
        }
        viewModel.err.observe(viewLifecycleOwner){err->
            binding.noInternetText.visibility = View.VISIBLE
            binding.noInternetText.text = err
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}