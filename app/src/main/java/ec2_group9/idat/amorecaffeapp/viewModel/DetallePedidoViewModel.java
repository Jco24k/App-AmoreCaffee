package ec2_group9.idat.amorecaffeapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ec2_group9.idat.amorecaffeapp.model.Auth;
import ec2_group9.idat.amorecaffeapp.model.DetallePedido;
import ec2_group9.idat.amorecaffeapp.retrofit.AmoreeCaffeeClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallePedidoViewModel extends AndroidViewModel {

    public MutableLiveData<DetallePedido> registroMutableLiveData
            = new MutableLiveData<>();

    public DetallePedidoViewModel(@NonNull Application application) {
        super(application);
    }

    public void registrarDetallePedido(
            DetallePedido requestRegistro){
        new AmoreeCaffeeClient().getDetPedidoInstance().create(requestRegistro)
                .enqueue(new Callback<DetallePedido>() {
                    @Override
                    public void onResponse(Call<DetallePedido> call, Response<DetallePedido> response) {
                        registroMutableLiveData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<DetallePedido> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }


}
