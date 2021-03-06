package com.tubes.ayamswasta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tubes.ayamswasta.data.adapter.RecyclerAdapter
import com.tubes.ayamswasta.data.model.Menu
import com.tubes.ayamswasta.data.repository.MenuMakananRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    //Deklarasi variabel
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>?=null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

            //Menentukan LayoutManager untuk RecyclerView
//            layoutManager = LinearLayoutManager(this)
//            recycler_view.LayoutManager = layoutManager
//
//            //Link kan RecyclerView dengan class Adapter
//            adapter = RecyclerAdapter()
//            recycler_view.adapter = adapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = layoutInflater.inflate(R.layout.fragment_menu, container, false)
        MenuMakananRepository.getMenu().getMenu("menu").enqueue(object : Callback<List<Menu>>{
            override fun onResponse(call: Call<List<Menu>>, response: Response<List<Menu>>) {
                if(response.isSuccessful){
                    val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
                    recyclerView.layoutManager = LinearLayoutManager(view.context)
                    recyclerView.adapter = RecyclerAdapter(response.body()!!, view.context)
                }
            }

            override fun onFailure(call: Call<List<Menu>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}