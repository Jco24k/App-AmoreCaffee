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

public interface DetallePedidoInterface {

    @GET(Routes.detallePedido)
    public Call<List<Producto>> findAll();

    @GET(Routes.detallePedido +"/{search}")
    public Call<Producto> findOne(@Path("search") String search);

    @POST(Routes.detallePedido +"/create")
    public Call<Producto> create(@Body Producto cliente);

    @PATCH(Routes.detallePedido)
    public Call<Producto> update(@Body Producto cliente);

    @DELETE(Routes.detallePedido +"/{id}")
    public Call<?> delete(@Path("id") String id);


}
