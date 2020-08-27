package com.example.hw14.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.hw14.R;

public abstract class Alert extends DialogFragment {

    protected EditText mEditTextPersianAlert;
    protected EditText mEditTextEnglishAlert;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void findView(View view) {

        mEditTextPersianAlert = view.findViewById(R.id.editText_alert_persian);
        mEditTextEnglishAlert = view.findViewById(R.id.editText_alert_english);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.alart_add_word, null);
        builder.setTitle(getTitle());
        builder.setIcon(getIcon());
        builder.setView(view);
        builder.setPositiveButton(getOkString(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                doOrder();
                setResult();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        findView(view);
        return builder.create();
    }

    protected abstract int getOkString() ;

    protected abstract int getIcon();

    protected abstract String getTitle();

    private void setResult() {
        getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                Activity.RESULT_OK,
                new Intent());
        dismiss();
    }

    protected abstract void doOrder();
 /*     RepositoryEP.newInstance(getActivity()).addTranslate(
            mEditTextEnglish.getText().toString(),
                mEditTextPersian.getText().toString()
        );*/
}
