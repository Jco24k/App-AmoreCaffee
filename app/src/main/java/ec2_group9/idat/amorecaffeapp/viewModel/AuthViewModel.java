package ec2_group9.idat.amorecaffeapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ec2_group9.idat.amorecaffeapp.model.Auth;
import ec2_group9.idat.amorecaffeapp.retrofit.AmoreeCaffeeClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends AndroidViewModel {

    public MutableLiveData<Auth> loginMutableLiveData
            = new MutableLiveData<>();

    public MutableLiveData<Auth> registroMutableLiveData
            = new MutableLiveData<>();

    public AuthViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(
            Auth requestLogin){
        new AmoreeCaffeeClient().getClienteInstance().login(requestLogin)
                .enqueue(new Callback<Auth>() {
                    @Override
                    public void onResponse(Call<Auth> call, Response<Auth> response) {
                        loginMutableLiveData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<Auth> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }


}
