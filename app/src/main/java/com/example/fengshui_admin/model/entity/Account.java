package com.example.fengshui_admin.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "account")
public class Account {
        @PrimaryKey
        @NonNull
        public String userName;
        public String password;
        public String accessToken;
        public String tokenType;
        public String refreshToken;

        public Account(String userName, String password, String accessToken, String tokenType, String refreshToken) {
                this.userName = userName;
                this.password = password;
                this.accessToken = accessToken;
                this.tokenType = tokenType;
                this.refreshToken = refreshToken;
        }
}
