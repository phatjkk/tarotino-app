package com.example.tarotino_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AdvancedFragment extends Fragment {

    public AdvancedFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advanced, container, false);
        Button btnGetCard = (Button) view.findViewById(R.id.btn_getCard);
        btnGetCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Tarot tarot = new Tarot(getActivity());
                Log.i("",tarot.getRandomCard());
            }
        });
        return view;
    }
}