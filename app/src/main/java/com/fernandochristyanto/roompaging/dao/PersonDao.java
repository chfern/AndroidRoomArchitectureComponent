package com.fernandochristyanto.roompaging.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.fernandochristyanto.roompaging.entity.Person;

import java.util.List;

import javax.sql.DataSource;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM persons")
    LiveData<List<Person>> getAll();

    /**
     * DataSource => Base class for loading pages of snapshot data into a pagedlist
     *
     * PagedList => A List which loads its data in chunks from a datasource.
     *  Items can be accessed with get(int), or loadAround(int) for further loading
     *
     * Here, we used the static Factory method in DataSource class that accepts 2 generics: <Identifier, Entity>
     * For the identifier, we use Integer because in Room, query results are numbered
     */
    @Query("SELECT * FROM PERSONS")
    android.arch.paging.DataSource.Factory<Integer, Person> getAllPaged();

    @Insert
    void insertAll(List<Person> personList);

    @Delete
    void delete(Person person);
}
