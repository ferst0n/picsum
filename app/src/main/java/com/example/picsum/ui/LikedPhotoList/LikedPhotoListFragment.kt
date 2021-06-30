package com.example.picsum.ui.LikedPhotoList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.picsum.R
import com.example.picsum.ui.RandomPhotoList.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LikedPhotoListFragment:Fragment() {



    private  var mContext: Context? = null
    private val likedPhotoListViewModel: LikedPhotoListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var randomPhotoListAdapter: RandomPhotoListAdapter



    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_liked_photo, container, false)

        recyclerView = root.findViewById(R.id.likedPhoto_recycler)
        recyclerView.layoutManager = LinearLayoutManager(mContext)

        setPagerAdapter()
        getData()

        Toast.makeText(context, "Свайп влево для дизлайка", Toast.LENGTH_SHORT).show()

        return root

    }

    private fun getData(){
        lifecycleScope.launch {
            likedPhotoListViewModel.flowLocalData.collectLatest { pagingData ->
                randomPhotoListAdapter.submitData(pagingData)
            }
        }
    }
    private fun setPagerAdapter() {

        val swipeHelper = object : LikedImageSwipeHelper(mContext) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when(direction){

                    ItemTouchHelper.LEFT->{

                        val pos = viewHolder.absoluteAdapterPosition
                        val img = randomPhotoListAdapter.deleteImageFromUI(pos)
                        likedPhotoListViewModel.deleteImage(img!!)
                        getData()

                    }


                }

            }
        }
        val touchHelper = ItemTouchHelper(swipeHelper)
        touchHelper.attachToRecyclerView(recyclerView)


        recyclerView.adapter = randomPhotoListAdapter
    }

}