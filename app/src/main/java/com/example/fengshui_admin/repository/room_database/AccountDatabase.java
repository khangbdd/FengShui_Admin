package com.example.fengshui_admin.repository.room_database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fengshui_admin.model.entity.Account;
import com.example.fengshui_admin.repository.room_database.dao.AccountDAO;

import kotlin.jvm.Volatile;

@Database(entities = Account.class, version = 2, exportSchema = false)
public abstract class AccountDatabase extends RoomDatabase {
    public abstract AccountDAO accountDao();
    @Volatile
    private static AccountDatabase INSTANCE = null;
    private static final String DATABASE_NAME = "account.db";
    public static AccountDatabase getInstance(Context context) {
        synchronized(context) {
            AccountDatabase instance = INSTANCE;
            if (instance == null) {
                instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AccountDatabase.class,
                        DATABASE_NAME
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
                INSTANCE = instance;
            }
            return instance;
        }
    }
}
