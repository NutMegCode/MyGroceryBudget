package com.nutmeg.mygrocerybudget.presentation.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class mainScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {

            setContent {
                mainScreen()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //if you want to do something special if the screen is popped back to


    }

}
