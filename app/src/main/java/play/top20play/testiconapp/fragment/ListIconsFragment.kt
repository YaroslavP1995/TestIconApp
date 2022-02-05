package play.top20play.testiconapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import play.top20play.testiconapp.R
import play.top20play.testiconapp.fragment.adapter.ListItemAdapter
import play.top20play.testiconapp.data.NeededData

class ListIconsFragment : Fragment(), ListItemAdapter.CardItemListener {


    private var listAdapter: ListItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_list_icons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvSearchResults: RecyclerView = view.findViewById(R.id.rvSearchResults)
        listAdapter = getListData()?.let {
            ListItemAdapter(
                list = it,
                this
            )
        }
        rvSearchResults.layoutManager = GridLayoutManager(requireContext(), 4)
        rvSearchResults.adapter = listAdapter
    }

    private fun getListData(): ArrayList<NeededData>? {
        return requireArguments().getSerializable(LIST_ITEMS) as? ArrayList<NeededData>
    }


    companion object {

        private const val LIST_ITEMS = "LIST_ITEMS"

        fun newInstance(
            list: ArrayList<NeededData>
        ): ListIconsFragment {
            return ListIconsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(LIST_ITEMS, list)
                }
            }
        }
    }

    override fun onCardItemClickListener(url: String) {
        FullImageScreen.show(childFragmentManager).apply {
            setUrl(url)
        }
    }
}