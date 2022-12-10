package ec2_group9.idat.amorecaffeapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.model.Categoria;
import ec2_group9.idat.amorecaffeapp.retrofit.AmoreeCaffeeClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaViewModel extends AndroidViewModel {

    public MutableLiveData<List<Categoria>> findAllMutableLiveData
            = new MutableLiveData<>();

    public MutableLiveData<Categoria> registroMutableLiveData
            = new MutableLiveData<>();

    public CategoriaViewModel(@NonNull Application application) {
        super(application);
    }

    public void findAll(){
        new AmoreeCaffeeClient().getCategoriaInstance().findAll()
                .enqueue(new Callback<List<Categoria>>() {
                    @Override
                    public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                        findAllMutableLiveData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<Categoria>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }


}
