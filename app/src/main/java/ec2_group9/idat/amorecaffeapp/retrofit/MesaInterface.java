package ec2_group9.idat.amorecaffeapp.retrofit;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.model.Mesa;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MesaInterface {

    @GET(Routes.mesa)
    public Call<List<Mesa>> findAll();

    @GET(Routes.mesa +"/{search}")
    public Call<Mesa> findOne(@Path("search") String search);

    @POST(Routes.mesa +"/create")
    public Call<Mesa> create(@Body Mesa cliente);

    @PATCH(Routes.mesa)
    public Call<Mesa> update(@Body Mesa cliente);

    @DELETE(Routes.mesa +"/{id}")
    public Call<?> delete(@Path("id") String id);


}
