package ec2_group9.idat.amorecaffeapp.retrofit;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.model.Producto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CabeceraPedidoInterface {

    @GET(Routes.cabeceraPedido)
    public Call<List<Producto>> findAll();

    @GET(Routes.cabeceraPedido +"/{search}")
    public Call<Producto> findOne(@Path("search") String search);

    @POST(Routes.cabeceraPedido +"/create")
    public Call<Producto> create(@Body Producto cliente);

    @PATCH(Routes.cabeceraPedido)
    public Call<Producto> update(@Body Producto cliente);

    @DELETE(Routes.cabeceraPedido +"/{id}")
    public Call<?> delete(@Path("id") String id);


}
