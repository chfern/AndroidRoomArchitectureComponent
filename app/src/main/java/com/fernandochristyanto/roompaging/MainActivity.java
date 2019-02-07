package com.fernandochristyanto.roompaging;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fernandochristyanto.roompaging.adapter.PersonAdapter;
import com.fernandochristyanto.roompaging.entity.Person;
import com.fernandochristyanto.roompaging.viewmodel.PersonViewModel;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvPerson;
    PersonViewModel personViewModel;
    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);

        adapter = new PersonAdapter(this);

        rvPerson = findViewById(R.id.rvPerson);
        rvPerson.setLayoutManager(new LinearLayoutManager(this));
        rvPerson.setAdapter(adapter);

        subscribeUi();
    }

    private void subscribeUi(){
        personViewModel.getPersonsLiveData().observe(this, new Observer<PagedList<Person>>() {
            @Override
            public void onChanged(@Nullable PagedList<Person> people) {
                adapter.submitList(people);
            }
        });
    }
}
