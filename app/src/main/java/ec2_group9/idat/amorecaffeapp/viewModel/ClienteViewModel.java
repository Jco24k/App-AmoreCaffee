package ec2_group9.idat.amorecaffeapp.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ec2_group9.idat.amorecaffeapp.model.Auth;
import ec2_group9.idat.amorecaffeapp.model.Cliente;
import ec2_group9.idat.amorecaffeapp.retrofit.AmoreeCaffeeClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteViewModel extends AndroidViewModel {

    public MutableLiveData<Cliente> registroMutableLiveData
            = new MutableLiveData<>();

    public ClienteViewModel(@NonNull Application application) {
        super(application);
    }

    public void create(
            Cliente requestClient){
        new AmoreeCaffeeClient().getClienteInstance().create(requestClient)
                .enqueue(new Callback<Cliente>() {
                    @Override
                    public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                        registroMutableLiveData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<Cliente> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }


}
