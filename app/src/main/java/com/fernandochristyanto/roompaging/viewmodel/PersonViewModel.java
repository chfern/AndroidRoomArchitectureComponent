package com.fernandochristyanto.roompaging.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.fernandochristyanto.roompaging.database.AppDatabase;
import com.fernandochristyanto.roompaging.entity.Person;

public class PersonViewModel extends AndroidViewModel {
    private LiveData<PagedList<Person>> personsLiveData;

    public PersonViewModel(@NonNull Application application) {
        super(application);

        DataSource.Factory<Integer, Person> factory = AppDatabase.getInstance(getApplication()).personDao().getAllPaged();
        LivePagedListBuilder<Integer, Person> pagedListBuilder = new LivePagedListBuilder<Integer, Person>(factory, 50);

        personsLiveData = pagedListBuilder.build();
    }

    public LiveData<PagedList<Person>> getPersonsLiveData(){
        return this.personsLiveData;
    }
}
