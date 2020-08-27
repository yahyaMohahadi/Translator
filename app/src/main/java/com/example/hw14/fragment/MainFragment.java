package com.example.hw14.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;

import com.example.hw14.R;
import com.example.hw14.repository.RepositoryEP;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    public static final int REQUEST_CODE_DDELETE = 3;
    EditText mEditTextEnglish;
    EditText mEditTextPersian;

    Button mButtonTranslate;

    RepositoryEP mRepository;

    public static final int REQUEST_CODE_add = 0;

    public MainFragment() {
    }


    public static MainFragment newInstance(Intent intent) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        updateRepository();
    }

    private void updateRepository() {
        mRepository = RepositoryEP.newInstance(getActivity().getApplicationContext());
    }

    private void initViewMenu() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(
                String.valueOf(mRepository
                        .numberOfWords())
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        findView(view);
        setOnCklick();
        return view;
    }

    private void setOnCklick() {
        mButtonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translate();
            }
        });
    }

    private void translate() {
        if (mEditTextEnglish.getText().toString().equals("") ||
                mEditTextEnglish.getText().toString().equals("(no result) ")) {
            mEditTextEnglish.setText(
                    mRepository.getTranslatePersian(
                            mEditTextPersian.getText().toString()
                    )
            );
        } else if (mEditTextPersian.getText().toString().equals("") ||
                mEditTextPersian.getText().toString().equals("(no result) ")
        ) {
            mEditTextPersian.setText(
                    mRepository.getTranslateEnglish(
                            mEditTextEnglish.getText().toString()
                    )
            );
        }
    }

   /* private void setOnCklick() {
        mEditTextPersian.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mEditTextEnglish.setText(
                        mRepository.getTranslatePersian(
                                mEditTextPersian.getText().toString()
                        )
                );
            }
        });

        mEditTextEnglish.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mEditTextPersian.setText("salam");

            *//*    mEditTextPersian.setText(
                        mRepository.getTranslatePersian(
                                mEditTextEnglish.getText().toString()
                        )
                );*//*
            }
        });
    }*/

    private void findView(View view) {
        mEditTextEnglish = view.findViewById(R.id.editText_english);
        mEditTextPersian = view.findViewById(R.id.editTextText_persian);
        mButtonTranslate = view.findViewById(R.id.button_translate);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        initViewMenu();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_add: {
                addNewWord();
                break;
            }
            case R.id.item_share: {
                shareWord();
                break;
            }
            case R.id.item_delete: {
                deleteWord();
                break;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void deleteWord() {
        Alert add = new DeleteDialog();
        add.setTargetFragment(this, REQUEST_CODE_DDELETE);
        add.show(getFragmentManager(), "TAGe");
    }

    private void shareWord() {
        String mimeType = "text/plain";
        String title = "share word";

        Intent shareIntent = ShareCompat.IntentBuilder.from(getActivity())
                .setType(mimeType)
                .setText(getShareText())
                .setChooserTitle(title)
                .getIntent();
        if (shareIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(shareIntent);
        }
    }

    private String getShareText() {
        return "this in translator and the meaning of " + mEditTextEnglish.getText().toString() +
                "is " + mEditTextPersian.getText().toString();
    }


    private void addNewWord() {
        Alert add = new AddDialog();
        add.setTargetFragment(this, REQUEST_CODE_add);
        add.show(getFragmentManager(), "TAGe");

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null || resultCode != Activity.RESULT_OK) {
            return;
        } else if (requestCode == REQUEST_CODE_add) {
            initViewMenu();
            updateRepository();
        } else if (requestCode == REQUEST_CODE_DDELETE) {
            initViewMenu();
            updateRepository();
        }
    }
}