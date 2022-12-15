package ec2_group9.idat.amorecaffeapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ec2_group9.idat.amorecaffeapp.model.Cliente;
import ec2_group9.idat.amorecaffeapp.model.Mesa;
import ec2_group9.idat.amorecaffeapp.retrofit.AmoreeCaffeeClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MesaViewModel extends AndroidViewModel {


    public MutableLiveData<Mesa> findOneMutableLiveData
            = new MutableLiveData<>();

    public MesaViewModel(@NonNull Application application) {
        super(application);
    }


    public void findOne(String id){
        new AmoreeCaffeeClient().getMesaInstance().findOne(id)
                .enqueue(new Callback<Mesa>() {
                    @Override
                    public void onResponse(Call<Mesa> call, Response<Mesa> response) {
                        findOneMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Mesa> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }


}
