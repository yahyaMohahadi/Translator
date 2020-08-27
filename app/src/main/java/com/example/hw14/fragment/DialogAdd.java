package com.example.hw14.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.hw14.R;
import com.example.hw14.repository.RepositoryEP;

import java.util.zip.Inflater;

public class DialogAdd extends DialogFragment {

    private EditText mEditTextPersian;
    private EditText mEditTextEnglish;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void findView(View view) {

        mEditTextPersian = view.findViewById(R.id.editText_alert_persian);
        mEditTextEnglish = view.findViewById(R.id.editText_alert_english);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.alart_add_word, null);
        builder.setTitle("Add new Word ");
        builder.setIcon(R.drawable.ic_add);
        builder.setView(view);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                add();
                setResult();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        findView(view);
        return builder.create();
    }

    private void setResult() {
       getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                Activity.RESULT_OK,
                new Intent());
        dismiss();
    }

    private void add() {
        RepositoryEP.newInstance(getActivity()).addTranslate(
                mEditTextEnglish.getText().toString(),
                mEditTextPersian.getText().toString()
        );
    }
}
