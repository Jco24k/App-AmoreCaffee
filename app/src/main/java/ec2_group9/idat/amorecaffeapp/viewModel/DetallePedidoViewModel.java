package ec2_group9.idat.amorecaffeapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ec2_group9.idat.amorecaffeapp.model.Auth;
import ec2_group9.idat.amorecaffeapp.model.DetallePedido;
import ec2_group9.idat.amorecaffeapp.retrofit.AmoreeCaffeeClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallePedidoViewModel extends AndroidViewModel {

    public MutableLiveData<List<DetallePedido>> registroMutableLiveData
            = new MutableLiveData<>();

    public DetallePedidoViewModel(@NonNull Application application) {
        super(application);
    }

    public void registrarAllDetallePedido(
            List<DetallePedido> requestRegistro){
        new AmoreeCaffeeClient().getDetPedidoInstance().createAll(requestRegistro)
                .enqueue(new Callback<List<DetallePedido>>() {
                    @Override
                    public void onResponse(Call<List<DetallePedido>> call, Response<List<DetallePedido>> response) {
                        registroMutableLiveData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<DetallePedido>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }


}
