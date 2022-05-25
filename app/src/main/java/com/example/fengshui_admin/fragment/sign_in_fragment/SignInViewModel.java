package com.example.fengshui_admin.fragment.sign_in_fragment;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fengshui_admin.model.dto.SignInDTO;
import com.example.fengshui_admin.model.dto.TokenDTO;
import com.example.fengshui_admin.repository.auth_service.AccountRepository;
import com.example.fengshui_admin.utils.LoginState;

import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.val;

@HiltViewModel
public class SignInViewModel extends ViewModel {

    public MutableLiveData<String> email  = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    public String emailErrorMessage = "";
    public String passwordErrorMessage = "";

    public  MutableLiveData<String>  toastMessage = new MutableLiveData<>();
    public  MutableLiveData<LoginState> loginState = new MutableLiveData<>();
    public TokenDTO token = null;
    private AccountRepository accountRepository;

    @Inject
    public SignInViewModel(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    public Boolean isValidPassword() {
        if (password.getValue().isEmpty()) {
            passwordErrorMessage = "Password can not empty";
            return false;
        } else {
            if (password.getValue().length() < 8) {
                passwordErrorMessage = "Password least than 8 character";
                return false;
            } else {
                passwordErrorMessage = null;
                return true;
            }
        }
    }

    public void onLoginClick() {
        if (email.getValue() != null && password.getValue() != null) {
            if (isValidEmail() && isValidPassword()) {
                signIn(email.getValue(), password.getValue());
            } else {
                toastMessage.setValue( "Email or password is invalid!");
            }
        } else {
            toastMessage.setValue("Email or password is invalid!");
        }
    }

    @SuppressLint("CheckResult")
    private void signIn(String email, String password) {
        loginState.setValue( LoginState.Loading);
        accountRepository.sellerSignIn(new SignInDTO(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<TokenDTO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TokenDTO tokenDTO) {
                        token = tokenDTO;
                        loginState.postValue(LoginState.Success);
                        toastMessage.setValue( "Login success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginState.postValue( LoginState.Wait);
                        e.printStackTrace();
                        Log.d("Sign In Error: ", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public Boolean isValidEmail() {
        if (email.getValue().isEmpty()) {
            emailErrorMessage = "Email can not empty";
            return false;
        } else {
            if (isEmailRightFormat(email.getValue().trim())) {
                emailErrorMessage = null;
                return true;
            } else {
                emailErrorMessage = "Invalid email";
                return false;
            }
        }
    }

    private Boolean isEmailRightFormat(String emailToCheck) {
        return Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(emailToCheck).matches();
    }
}
