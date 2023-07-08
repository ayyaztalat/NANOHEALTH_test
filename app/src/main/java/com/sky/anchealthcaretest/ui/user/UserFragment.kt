package com.sky.anchealthcaretest.ui.user

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.sky.anchealthcaretest.R
import com.sky.anchealthcaretest.databinding.FragmentUserBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UserFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentUserBinding?=null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewmodel = ViewModelProvider(this)[UserViewModel::class.java]

        _binding = FragmentUserBinding.inflate(inflater,container,false)
        val root: View = binding.root

        val textView: TextView = binding.textUser
        viewmodel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}