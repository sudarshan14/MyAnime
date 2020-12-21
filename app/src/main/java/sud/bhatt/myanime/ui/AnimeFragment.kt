package sud.bhatt.myanime.ui


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_anime_list_search.*
import kotlinx.android.synthetic.main.search_anime_toolbar.*
import kotlinx.android.synthetic.main.search_toolbar.*
import sud.bhatt.myanime.R
import sud.bhatt.myanime.data.MyAnimeData
import sud.bhatt.myanime.ui.adapters.AnimeDataAdapter
import sud.bhatt.myanime.viewmodel.AnimeViewModel
import java.lang.Error


class AnimeFragment : Fragment(R.layout.fragment_anime_list_search) {

    
    private val STANDARD_APPBAR = 0
    private val SEARCH_APPBAR = 1
    private var appBarState = 0

    private val viewContactsBar: AppBarLayout? = null
//    private var searchBar: AppBarLayout = AppBarLayout()

    private val viewModel: AnimeViewModel by viewModels()
    private var animeData: MutableList<MyAnimeData> = mutableListOf()
    private lateinit var animeDataAdapter: AnimeDataAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        setAppBaeState(STANDARD_APPBAR);

        animeDataAdapter = AnimeDataAdapter(animeData)
        recycle_view_anime.apply {
            adapter = animeDataAdapter
            layoutManager = StaggeredGridLayoutManager(3, VERTICAL)
//            layoutManager = LinearLayoutManager(activity)
        }

        search_icon.setOnClickListener {
            toggleToolBarState()
        }
        back_arrow.setOnClickListener {
            toggleToolBarState()

        }
        anime_search_query.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


                viewModel.setSearchQuery(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

    }


    override fun onResume() {
        super.onResume()
        setAppBaeState(STANDARD_APPBAR)
    }


    private fun toggleToolBarState() {
        if (appBarState == STANDARD_APPBAR) {
            setAppBaeState(SEARCH_APPBAR);
        } else {
            setAppBaeState(STANDARD_APPBAR);
        }
    }

    private fun setAppBaeState(state: Int) {
        appBarState = state
        val im: InputMethodManager =
            (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)

        if (appBarState == STANDARD_APPBAR) {
            search_anime_toolbar.visibility = View.GONE
            search_toolbar.visibility = View.VISIBLE



            try {
                im.hideSoftInputFromWindow(view?.windowToken, 0)

            } catch (e: Error) {

            }
        } else {
            search_anime_toolbar.visibility = View.VISIBLE
            search_toolbar.visibility = View.GONE
            im.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }


    private fun setObservers() {
        viewModel.getAnimes().observe(viewLifecycleOwner) {
            animeData.clear()
            animeData.addAll(it.animeData)
            animeDataAdapter.notifyDataSetChanged()
        }
        viewModel.getFailure().observe(viewLifecycleOwner) {
            println(it)
        }
    }
}