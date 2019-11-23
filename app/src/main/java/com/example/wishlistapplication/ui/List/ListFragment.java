package com.example.wishlistapplication.ui.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.wishlistapplication.Model.NoteCard;
import com.example.wishlistapplication.Adapter.NoteAdapter;
import com.example.wishlistapplication.R;
import com.example.wishlistapplication.ViewModel.NoteCardViewModel;
import com.example.wishlistapplication.ui.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements NoteAdapter.OnNoteListener {

    private NoteCardViewModel mViewModel;
    RecyclerView mNoteList;
    NoteAdapter mNoteAdapter;
    ArrayList<NoteCard> noteCards = new ArrayList<>();
    private View root;
    private FloatingActionButton btn;

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main_page, container, false);

        Toolbar myToolbar = (Toolbar) root.findViewById(R.id.tb);

        mNoteList = root.findViewById(R.id.rv);
        mNoteList.hasFixedSize();
        mNoteList.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel = ViewModelProviders.of(this).get(NoteCardViewModel.class);
        mViewModel.getAllNotes().observe(this, new Observer<List<NoteCard>>() {
            @Override
            public void onChanged(List<NoteCard> lists) {
                noteCards = new ArrayList<>();
                for (int i = 0; i < lists.size(); i++) {
                    noteCards.add(lists.get(i));
                }
                mNoteAdapter = new NoteAdapter(noteCards);
                // new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mNoteList);

                mNoteList.setAdapter(mNoteAdapter);


            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                mViewModel.delete(mNoteAdapter.getNoteAt(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(mNoteList);

/*mNoteAdapter.setOnNoteClickListener(new NoteAdapter.OnNoteListener() {
    @Override
    public void onNoteClick(NoteCard note) {

       *//* FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new NoteDetailFragment());
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();*//*

        Intent intent = new Intent(getContext(),NoteDetailFragment.class);
        intent.putExtra(NoteDetailFragment.EXTRA_ID,note.getId());
        intent.putExtra(NoteDetailFragment.EXTRA_TITLE,note.getTitle());
        intent.putExtra(NoteDetailFragment.EXTRA_TRANSLATION,note.getTranslartion());
        intent.putExtra(NoteDetailFragment.EXTRA_NOTE,note.getNotes());
        startActivityForResult(intent,EDIT_NOTE_REQUEST);

    }
});*/

        FabOnClick();
        onResume();
        return root;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == getActivity().RESULT_OK) {
            String title = data.getStringExtra(NoteDetailFragment.EXTRA_TITLE);
            String translation = data.getStringExtra(NoteDetailFragment.EXTRA_TRANSLATION);
            String notes = data.getStringExtra(NoteDetailFragment.EXTRA_NOTE);

            NoteCard note = new NoteCard(title, translation, notes);
            mViewModel.insert(note);

            Toast.makeText(getContext(), "Note saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == getActivity().RESULT_OK) {
            int id = data.getIntExtra(NoteDetailFragment.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(NoteDetailFragment.EXTRA_TITLE);
            String translation = data.getStringExtra(NoteDetailFragment.EXTRA_TRANSLATION);
            String notes = data.getStringExtra(NoteDetailFragment.EXTRA_NOTE);

            NoteCard note = new NoteCard(title, translation, notes);
            note.setId(id);
            mViewModel.update(note);

            Toast.makeText(getContext(), "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }


    private void FabOnClick() {
        btn = (FloatingActionButton) root.findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new CreateNoteFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ((MainActivity) getActivity()).onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onNoteClick(NoteCard note) {

        //noteCards.get(position);
        /*FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new ListInfoFragment());
        fragmentTransaction.commit();*/
    }


}

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        // TODO: Use the ViewModel
    }*/




