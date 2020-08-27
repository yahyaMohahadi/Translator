package com.example.hw14.fragment;

import android.app.AlertDialog;
import android.content.Context;

import com.example.hw14.R;
import com.example.hw14.repository.RepositoryEP;

public class AddDialog extends Alert {

    @Override
    protected int getOkString() {
        return android.R.string.ok;
    }

    @Override
    protected int getIcon() {
        return R.drawable.ic_add;
    }

    @Override
    protected String getTitle() {
        return "Add new word";
    }

    @Override
    protected void doOrder() {
        RepositoryEP.newInstance(getActivity()).addTranslate(
                mEditTextEnglishAlert.getText().toString(),
                mEditTextPersianAlert.getText().toString()
        );
    }

}
