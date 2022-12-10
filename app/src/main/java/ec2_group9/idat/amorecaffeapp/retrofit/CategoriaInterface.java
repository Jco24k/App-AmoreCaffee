package ec2_group9.idat.amorecaffeapp.retrofit;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.model.Categoria;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoriaInterface {


    @GET(Routes.categoria)
    public Call<List<Categoria>> findAll();

    @GET(Routes.categoria +"/{search}")
    public Call<Categoria> findOne(@Path("search") String search);

    @POST(Routes.categoria +"/create")
    public Call<Categoria> create(@Body Categoria categoria);

    @PATCH(Routes.categoria)
    public Call<Categoria> update(@Body Categoria categoria);

    @DELETE(Routes.categoria +"/{id}")
    public Call<?> delete(@Path("id") String id);



}
