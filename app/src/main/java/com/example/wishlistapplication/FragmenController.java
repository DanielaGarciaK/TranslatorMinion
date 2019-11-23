package com.example.wishlistapplication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wishlistapplication.R;
import com.example.wishlistapplication.ui.List.ListFragment;

public class FragmenController {
    private static FragmenController instance;
    public static FragmenController getInstance() {
        if(instance == null)
        {
            instance = new FragmenController();
        }
        return instance;
    }

    private FragmentManager fm;
    public void setFm(FragmentManager fm) {
        this.fm = fm;
    }

    private FragmenController()
    {}

    public void ChangeFragment(Fragment newFragment){
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, newFragment);
        fragmentTransaction.commit();

    }
}
