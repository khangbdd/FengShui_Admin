package com.example.fengshui_admin.repository.auth_service;

import com.example.fengshui_admin.model.dto.SignInDTO;
import com.example.fengshui_admin.model.dto.TokenDTO;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountService {
    @POST("auth/seller-sign-in")
    public Observable<TokenDTO> sellerSignIn(
            @Body SignInDTO signInDTO
    );
}
