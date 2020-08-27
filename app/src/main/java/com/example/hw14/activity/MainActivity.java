package com.example.hw14.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.hw14.R;
import com.example.hw14.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.main_fragment_place, MainFragment.newInstance(new Intent()
        )).commit();

    }

/*    public void setSubtlte(){
        getSupportActionBar().setSubtitle("dsf");
    }*/

}