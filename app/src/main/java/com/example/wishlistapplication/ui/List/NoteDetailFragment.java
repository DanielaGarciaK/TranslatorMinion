package com.example.wishlistapplication.ui.List;

import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wishlistapplication.Model.NoteCard;
import com.example.wishlistapplication.R;
import com.example.wishlistapplication.ViewModel.NoteDetailViewModel;

public class NoteDetailFragment extends Fragment {

    private NoteDetailViewModel mViewModel;

    public static NoteDetailFragment newInstance() {
        return new NoteDetailFragment();
    }

   public static final String EXTRA_ID =
            "com.codinginflow.architectureexample.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.codinginflow.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_TRANSLATION =
            "com.codinginflow.architectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_NOTE =
            "com.codinginflow.architectureexample.EXTRA_DESCRIPTION";

    private EditText title;
    private EditText translation;
    private EditText note;
    private View root;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root= inflater.inflate(R.layout.note_detail_fragment, container, false);

        title = root.findViewById(R.id.newword);
        translation = root.findViewById(R.id.translation);
        note = root.findViewById(R.id.notes);

        Intent intent = getActivity().getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            getActivity().setTitle("Edit Note");
            title.setText(intent.getStringExtra(EXTRA_TITLE));
            translation.setText(intent.getStringExtra(EXTRA_TRANSLATION));
            note.setText(intent.getStringExtra(EXTRA_NOTE));
        }else{
            getActivity().setTitle("Add Note");
        }

        return root;

    }

    private void saveNote() {
        String titlee = title.getText().toString();
        String trans = translation.getText().toString();
        String notee = note.getText().toString();

        if (titlee.trim().isEmpty()){
            Toast.makeText(getContext(), "Please insert a title", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,titlee);
        data.putExtra(EXTRA_TRANSLATION, trans);
        data.putExtra(EXTRA_NOTE, notee);

        int id = getActivity().getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        getActivity().setResult(Activity.RESULT_OK,data);
        getActivity().finish();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NoteDetailViewModel.class);
        // TODO: Use the ViewModel*/
    }

}
