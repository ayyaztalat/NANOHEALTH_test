package com.sky.anchealthcaretest.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.sky.anchealthcaretest.databinding.FragmentHomeBinding
import com.sky.anchealthcaretest.interfaces.ItemClicked
import com.sky.anchealthcaretest.model.GetAllProducts
import com.sky.anchealthcaretest.ui.home.adapter.ProductsAdapter
import com.sky.anchealthcaretest.ui.singleProduct.SingleProductActivity

class HomeFragment : Fragment(), ItemClicked {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    var arrayList = ArrayList<GetAllProducts>()

    var mContext: Context? = null
    var mActivity: Activity? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
        mActivity = context as Activity
    }

    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter.getContext(mContext!!)

        homeViewModel.getArrayList.observe(viewLifecycleOwner) {
            this.arrayList = it
            adapter.updateAdapter(arrayList)
        }
        return root
    }

    val adapter = ProductsAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(mContext!!, RecyclerView.VERTICAL, false)
        binding.mainProductList.layoutManager = layoutManager
        binding.mainProductList.setHasFixedSize(true)
        binding.mainProductList.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        homeViewModel.getArrayList.removeObservers(viewLifecycleOwner)
    }

    override fun itemClicked(id: Int) {
        mContext!!.startActivity(
            Intent(mContext!!, SingleProductActivity::class.java)
                .putExtra("id", id)
        )
    }
}