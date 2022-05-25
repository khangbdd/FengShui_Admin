package com.example.fengshui_admin.repository.room_database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fengshui_admin.model.entity.Account;

@Dao
public interface AccountDAO {

    @Insert
    void insertAccount(Account account);

    @Delete
    void deleteAccount(Account account);

    @Query("UPDATE account SET accessToken = :accessToken")
    void updateAccessToken(String accessToken);

    @Query("SELECT * FROM account Limit 1")
    Account getAccount();
}
