package com.example.wishlistapplication.ui.List;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wishlistapplication.Model.NoteCard;
import com.example.wishlistapplication.R;
import com.example.wishlistapplication.ViewModel.NoteCardViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CreateNoteFragment extends Fragment {

    public static final String EXTRA_TITLE =
            "com.codinginflow.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_TRANSLATION =
            "com.codinginflow.architectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_NOTE =
            "com.codinginflow.architectureexample.EXTRA_PRIORITY";


    private EditText mtitle;
    private EditText mdesc;
    private TextInputEditText mnotes;
    private Button mbtn;
    private View root;
    private NoteCardViewModel viewModel;
    NoteCard note;



    public static CreateNoteFragment newInstance() {
        return new CreateNoteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_create_note, container, false);

        mtitle = root.findViewById(R.id.newword);
        mdesc = root.findViewById(R.id.translation);
        mnotes = root.findViewById(R.id.notes);
        mbtn = root.findViewById(R.id.submit);
        viewModel = ViewModelProviders.of(this).get(NoteCardViewModel.class);

        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                saveNote();


                /*Bundle args = new Bundle();
                args.putSerializable(mtitle.getText().toString(), note.getTitle());
                args.putSerializable(mdesc.getText().toString(),note.getTranslartion());
                args.putSerializable(mnotes.getText().toString(),note.getNotes());
*/
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new ListFragment());
                fragmentTransaction.commit();
            }

        });

        return root;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void saveNote(){
        String title  = mtitle.getText().toString();
        String translation = mdesc.getText().toString();
        String notes = mnotes.getText().toString();

       NoteCard note = new NoteCard(title, translation, notes);

        if(title.isEmpty()){
            Toast.makeText(getContext(),"please insert Title",Toast.LENGTH_SHORT).show();
            return;
        }
        /*Intent i = new Intent();
        i.putExtra(EXTRA_TITLE,title);
        i.putExtra(EXTRA_TRANSLATION,translation);
        i.putExtra(EXTRA_NOTE,title);
        getActivity().setResult(Activity.RESULT_OK,i);
        getActivity().finish();*/

        else{

            viewModel.insert(note);
        }





    }




}
