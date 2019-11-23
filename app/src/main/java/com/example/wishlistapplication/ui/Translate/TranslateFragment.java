package com.example.wishlistapplication.ui.Translate;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wishlistapplication.Model.Translation;
import com.example.wishlistapplication.R;
import com.example.wishlistapplication.ViewModel.TranslateViewModel;

public class TranslateFragment extends Fragment {

    TranslateViewModel mViewModel;
    EditText toTranslate;
    TextView translated;
    Button translatebtn;
    View root;

    public static TranslateFragment newInstance() {
        return new TranslateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_translate, container, false);



                return root;
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toTranslate = root.findViewById(R.id.wordToTranslate);
        translated = root.findViewById(R.id.translation);
        translatebtn = root.findViewById(R.id.btnTranslate);

        mViewModel = ViewModelProviders.of(this).get(TranslateViewModel.class);
        mViewModel.getTranslation().observe(this, new Observer<Translation>() {
            @Override
            public void onChanged(Translation translation) {
                translated.setText(translation.getTranslation());
            }
        });

        translatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTranslation(view);
            }
        });
    }


    public void updateTranslation(View view){
        mViewModel.updateTranslation(toTranslate.getText().toString());
    }

}
