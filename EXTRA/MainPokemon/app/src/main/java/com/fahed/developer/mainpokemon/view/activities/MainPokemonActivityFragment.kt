package com.fahed.developer.mainpokemon.view.activities

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.fahed.developer.mainpokemon.R
import com.fahed.developer.mainpokemon.data.model.Pokemon
import com.fahed.developer.mainpokemon.view.adapters.PokemonAdapter
import com.fahed.developer.mainpokemon.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main_pokemon.*
import kotlinx.android.synthetic.main.layout_error.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A placeholder fragment containing a simple view.
 */
class MainPokemonActivityFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var adapter: PokemonAdapter
    private var loadingScroll: Boolean = true

    companion object{
        private const val SPAN_COUNT = 3
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupUI()
    }

    private fun setupViewModel() {
        viewModel.pokemons.observe(this,renderPokemons)

        viewModel.isViewLoading.observe(this,isViewLoadingObserver)
        viewModel.onMessageError.observe(this,onMessageErrorObserver)
        viewModel.isEmptyList.observe(this,emptyListObserver)
    }

    private fun setupUI() {
        adapter = PokemonAdapter(viewModel.pokemons.value?: emptyList())

        recyclerViewPokemon.layoutManager= GridLayoutManager(context,SPAN_COUNT)
        recyclerViewPokemon.adapter= adapter

        hideTextViewLoading()
        showScroll()
    }

    private fun showScroll() {
        nestedScrollViewMainPokemon.viewTreeObserver.addOnScrollChangedListener {
            var view = nestedScrollViewMainPokemon.getChildAt(nestedScrollViewMainPokemon.childCount - 1) as View
            var diff: Int = (view.bottom - (nestedScrollViewMainPokemon.height + nestedScrollViewMainPokemon.scrollY))
            if (diff == 0) {
                if (loadingScroll) {
                    loadingScroll = false
                    showTextViewLoading()
                    viewModel.loadPokemons()
                }
            }
        }
    }

    private fun showTextViewLoading() {
        textViewLoadListPokemon.visibility = View.VISIBLE
    }

    private fun hideTextViewLoading() {
        textViewLoadListPokemon.visibility = View.GONE
    }

    //observers
    private val renderPokemons= Observer<List<Pokemon>> {
        Log.e("TAG", "data updated $it")
        layoutError.visibility=View.GONE
        layoutEmpty.visibility=View.GONE
        hideTextViewLoading()
        adapter.update(it)

        loadingScroll = true
    }

    private val isViewLoadingObserver= Observer<Boolean> {
        Log.e("TAG", "isViewLoading $it")
        val visibility=if(it)View.VISIBLE else View.GONE
        progressBar.visibility= visibility
    }

    private val onMessageErrorObserver= Observer<Any> {
        Log.e("TAG", "onMessageError $it")
        layoutError.visibility=View.VISIBLE
        layoutEmpty.visibility=View.GONE
        textViewError.text= "Error $it"
    }

    private val emptyListObserver= Observer<Boolean> {
        Log.e("TAG", "emptyListObserver $it")
        layoutEmpty.visibility=View.VISIBLE
        layoutError.visibility=View.GONE
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadPokemons()
    }

    override fun onStop() {
        viewModel.stop()
        super.onStop()
    }
}
