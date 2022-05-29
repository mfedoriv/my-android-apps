package org.mfedorivapps.myhealthapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import org.mfedorivapps.myhealthapp.databinding.FragmentRootBinding
import java.lang.System.exit

class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding:FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)
        binding.openYellowBoxButton.setOnClickListener {
            openBox(Color.rgb(255, 255, 200), getString(R.string.yellow))
        }
        binding.openGreenBoxButton.setOnClickListener {
            openBox(Color.rgb(200, 255, 200), getString(R.string.green))
        }
        // listening for the results from BoxFragment
        val liveData = findNavController().currentBackStackEntry?.savedStateHandle
            ?.getLiveData<Int>(BoxFragment.EXTRA_RANDOM_NUMBER)
        liveData?.observe(viewLifecycleOwner) { randomNumber ->
            if (randomNumber != null) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.generated_number, randomNumber),
                    Toast.LENGTH_SHORT
                ).show()
                // clear data to show Toast only once with rotating screen -> because savedStateEntry creates again after rotation
                liveData.value = null
            }
        }

    }

    private fun openBox(color:Int, colorName: String) {

        val direction = RootFragmentDirections.actionRootFragmentToBoxFragment(color, colorName)

        findNavController().navigate(
            direction, // arguments for the destination
            // optional additional options, example of simple animation:
            navOptions {
                anim {
                    enter = R.anim.enter
                    exit = R.anim.exit
                    popEnter = R.anim.pop_enter
                    popExit = R.anim.pop_exit
                }
            }
        )
    }
}