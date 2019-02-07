package com.fernandochristyanto.roompaging.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.fernandochristyanto.roompaging.dao.PersonDao;
import com.fernandochristyanto.roompaging.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Database(version = 1, entities = {Person.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PersonDao personDao;

        public PopulateDbAsyncTask(AppDatabase db){
            personDao = db.personDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<Person> dummyDatas = new ArrayList<>();
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dummy 1"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dummy 2"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dummy 3"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dummy 4"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 5"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 6"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 7"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 8"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 9"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 10"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 11"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 12"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 13"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 14"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 15"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 17"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 18"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 19"));
            dummyDatas.add(new Person(UUID.randomUUID().toString(), "Dumym 20"));
            personDao.insertAll(dummyDatas);
            return null;
        }
    }
}
