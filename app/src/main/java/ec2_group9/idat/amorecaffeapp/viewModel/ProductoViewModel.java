package ec2_group9.idat.amorecaffeapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ec2_group9.idat.amorecaffeapp.model.Producto;
import ec2_group9.idat.amorecaffeapp.retrofit.AmoreeCaffeeClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoViewModel extends AndroidViewModel {

    public MutableLiveData<List<Producto>> findAllMutableLiveData
            = new MutableLiveData<>();

    public MutableLiveData<List<Producto>> findProductCategMutableLiveData
            = new MutableLiveData<>();

    public ProductoViewModel(@NonNull Application application) { super(application); }

    public void findAll() {
        new AmoreeCaffeeClient().getProductoInstance().findAll()
                .enqueue(new Callback<List<Producto>>() {
                    @Override
                    public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                        findAllMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Producto>> call, Throwable t) {

                    }
                });
    }

    public void findProductCateg(String categoria){
        new AmoreeCaffeeClient().getProductoInstance().findProductCateg(categoria)
                .enqueue(new Callback<List<Producto>>() {
                    @Override
                    public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                        findProductCategMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Producto>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
