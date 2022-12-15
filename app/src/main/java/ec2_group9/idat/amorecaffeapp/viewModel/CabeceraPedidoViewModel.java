package ec2_group9.idat.amorecaffeapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ec2_group9.idat.amorecaffeapp.model.CabeceraPedido;
import ec2_group9.idat.amorecaffeapp.model.Cliente;
import ec2_group9.idat.amorecaffeapp.retrofit.AmoreeCaffeeClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CabeceraPedidoViewModel extends AndroidViewModel {

    public MutableLiveData<CabeceraPedido> registroMutableLiveData
            = new MutableLiveData<>();


    public CabeceraPedidoViewModel(@NonNull Application application) {
        super(application);
    }

    public void create(
            CabeceraPedido requestCabPedido){
        new AmoreeCaffeeClient().getCabPedidoInstance().create(requestCabPedido)
                .enqueue(new Callback<CabeceraPedido>() {
                    @Override
                    public void onResponse(Call<CabeceraPedido> call, Response<CabeceraPedido> response) {
                        registroMutableLiveData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<CabeceraPedido> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }


}
