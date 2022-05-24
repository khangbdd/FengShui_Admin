package com.example.fengshui_admin.fragment.sign_in_fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fengshui_admin.R;
import com.example.fengshui_admin.activity.MainActivity;
import com.example.fengshui_admin.databinding.FragmentSignInBinding;
import com.example.fengshui_admin.dialog.LoadingDialog;
import com.example.fengshui_admin.fragment.order_fragment.OrderFragment;
import com.example.fengshui_admin.fragment.order_fragment.OrderViewModel;
import com.example.fengshui_admin.model.dto.SignInDTO;
import com.example.fengshui_admin.model.dto.TokenDTO;
import com.example.fengshui_admin.model.entity.Account;
import com.example.fengshui_admin.repository.auth_service.AccountRepository;
import com.example.fengshui_admin.repository.room_database.AccountDatabase;
import com.example.fengshui_admin.utils.LoginState;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import lombok.val;

@AndroidEntryPoint
public class SignInFragment extends Fragment {

    private SignInViewModel viewModel;
    private  FragmentSignInBinding binding;
    private FragmentManager fragmentManager;
    private LoadingDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SignInViewModel.class);
        fragmentManager = getParentFragmentManager();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false);
        dialog = new LoadingDialog(getActivity());
        setCheckingEmail();
        setCheckingPassword();
        onLoginStateChange();
        toastMessageChange();
        setOnTextChange();
        binding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.onLoginClick();
            }
        });
        return binding.getRoot();
    }

    private void onLoginStateChange() {
        viewModel.loginState.observe(this.getViewLifecycleOwner(), it -> {
        if (it == LoginState.Wait) {
            dialog.dismissDialog();
        } else {
            if (it == LoginState.Loading) {
                dialog.startLoading();
            } else {
                dialog.dismissDialog();
                if (viewModel.token != null && viewModel.email.getValue() != null && viewModel.password.getValue() != null) {
                    Account account = new Account(
                                    viewModel.email.getValue(),
                            viewModel.password.getValue(),
                            viewModel.token.getAccessToken(),
                            viewModel.token.getTokenType(),
                            viewModel.token.getRefreshToken()
                            );
                    AccountDatabase.getInstance(this.requireContext()).accountDao.insertAccount(
                            account
                    );
                    Log.d("0000000000000000", viewModel.token.getAccessToken());
                    navigateTo(new OrderFragment());
                } else {
                    Toast.makeText(this.getContext(), "An error occur!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        });
    }

    private void navigateTo(Fragment desFragment){
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_manager, desFragment)
                .commit();
    }

    private void setCheckingEmail() {
        viewModel.email.observe(this.getViewLifecycleOwner(), email -> {
            viewModel.isValidEmail();
            binding.emailLayout.setError(viewModel.emailErrorMessage);
        });
    }

    private void setCheckingPassword() {
        viewModel.password.observe(this.getViewLifecycleOwner(), password -> {
                viewModel.isValidPassword();
                binding.passwordLayout.setError(viewModel.passwordErrorMessage);
        });
    }

    private void toastMessageChange() {
        viewModel.toastMessage.observe(this.getViewLifecycleOwner(), it -> {
                Toast.makeText(this.requireContext(), it, Toast.LENGTH_SHORT).show();
        });
    }

    private void setOnTextChange()
    {
        binding.emailEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.email.setValue(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.password.setValue(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}