package ec2_group9.idat.amorecaffeapp.retrofit;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.model.Auth;
import ec2_group9.idat.amorecaffeapp.model.Cliente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClienteInterface {

    @POST(Routes.login)
    public Call<Auth> login(@Body Auth auth);

    @GET(Routes.cliente)
    public Call<List<Cliente>> findAll();

    @GET(Routes.cliente +"/{search}")
    public Call<Cliente> findOne(@Path("search") String search);

    @POST(Routes.cliente +"/create")
    public Call<Cliente> create(@Body Cliente cliente);

    @PATCH(Routes.cliente +"/{id}")
    public Call<Cliente> update(@Body Cliente cliente, @Path("id") String id);

    @DELETE(Routes.cliente +"/{id}")
    public Call<?> delete(@Path("id") String id);



}
