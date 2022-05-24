package com.example.fengshui_admin.repository.auth_service;

import com.example.fengshui_admin.model.dto.SignInDTO;
import com.example.fengshui_admin.model.dto.TokenDTO;
import com.example.fengshui_admin.repository.RetrofitInstance;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AccountRepository {

    private AccountService accountService;

    @Inject
    public AccountRepository() {
        accountService = RetrofitInstance.getInstance().retrofit.create(AccountService.class);
    }

    public Observable<TokenDTO> sellerSignIn(SignInDTO signInDTO){
        return accountService.sellerSignIn(signInDTO);
    }
}
