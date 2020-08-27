package com.example.hw14.fragment;

import android.util.Log;

import com.example.hw14.R;
import com.example.hw14.repository.RepositoryEP;

public class DeleteDialog extends Alert {
    @Override
    protected int getOkString() {
        return R.string.delete;
    }

    @Override
    protected int getIcon() {
        return R.drawable.ic_delete;
    }

    @Override
    protected String getTitle() {
        return "Delete a word";
    }

    @Override
    protected void doOrder() {
        Log.d("QQQ",mEditTextEnglishAlert.getText().toString()+"   "+
                mEditTextPersianAlert.getText().toString());
        RepositoryEP.newInstance(getActivity()).deleteWord(
                mEditTextEnglishAlert.getText().toString(),
                mEditTextPersianAlert.getText().toString()
        );
    }
}
