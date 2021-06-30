package com.example.picsum.ui.RandomPhotoList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.picsum.R
import com.example.picsum.data.model.Image
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@Suppress("DEPRECATION")
@AndroidEntryPoint
class RandomPhotoListFragment: Fragment(){

    private var mContext: Context? = null
    private val randomPhotoListViewModel: RandomPhotoListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView


    @Inject lateinit var randomPhotoListAdapter: RandomPhotoListAdapter



    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_photo_list, container, false)

        recyclerView = root.findViewById(R.id.randomPhoto_recycler)
        recyclerView.layoutManager = LinearLayoutManager(mContext)

        setPagerAdapter()
        getData()


        Toast.makeText(context, "Свайп вправо для лайка", Toast.LENGTH_SHORT).show()
        return root

    }


    private fun getData(){
        lifecycleScope.launch {
            randomPhotoListViewModel.flowRemoteData.collectLatest { pagingData ->
                randomPhotoListAdapter.submitData(pagingData)
            }
        }
    }
    private fun setPagerAdapter() {

        val swipeHelper = object : RandomImageSwipeHelper(mContext) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when(direction){

                    ItemTouchHelper.RIGHT->{

                        val pos = viewHolder.absoluteAdapterPosition
                        val img = randomPhotoListAdapter.likeImage(pos)
                        println(img)
                        randomPhotoListViewModel.insertImage(img!!)

                    }

                }

            }
        }
        val touchHelper = ItemTouchHelper(swipeHelper)
        touchHelper.attachToRecyclerView(recyclerView)


        recyclerView.adapter = randomPhotoListAdapter
    }


}