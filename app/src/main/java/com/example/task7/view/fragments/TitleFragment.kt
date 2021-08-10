package com.example.task7.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.task7.R
import com.example.task7.databinding.FragmentTitleBinding
import com.example.task7.model.models.Note
import com.example.task7.repository.RepositoryImpl
import com.example.task7.utils.NOTE_DATA
import com.example.task7.view.activities.MainActivity
import com.example.task7.view.adapters.NoteRecyclerAdapter
import com.example.task7.viewmodel.TitleNotesViewModel
import com.example.task7.viewmodel.TitleNotesViewModelFactory

class TitleFragment : Fragment() {

    private val viewModelFactory: TitleNotesViewModelFactory by lazy {
        TitleNotesViewModelFactory(
            RepositoryImpl.getRepository(requireContext())
        )
    }
    private val viewModel: TitleNotesViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentTitleBinding
    private lateinit var adapter: NoteRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)
        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)
        bindInit()
        subscribeToVM()

        return binding.root
    }

    private fun bindInit() {
        binding.lifecycleOwner = this
        binding.newNoteBtn.setOnClickListener {
            (requireActivity() as MainActivity).navController.navigate(R.id.action_titleFragment_to_noteContentFragment)
        }

        adapter = NoteRecyclerAdapter(requireContext()) { note ->
            openNote(note)
        }
        binding.recycler.adapter = adapter
    }

    private fun subscribeToVM() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.run {
                    viewModel.filterBySearch(this).observe(viewLifecycleOwner, {
                        adapter.setData(it)
                    })
                }
                return false
            }
        })

        viewModel.getAllNotes().observe(viewLifecycleOwner,
            { notes ->
                adapter.setData(notes)
            })
    }

    private fun openNote(note: Note) {
        val bundle = Bundle().apply { putParcelable(NOTE_DATA, note) }
        (requireActivity() as MainActivity).navController.navigate(
            R.id.action_titleFragment_to_noteContentFragment,
            bundle
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_download ->
                viewModel.downloadRandomNote()
        }
        return super.onOptionsItemSelected(item)
    }
}