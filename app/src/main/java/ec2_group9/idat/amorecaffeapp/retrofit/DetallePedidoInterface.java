package ec2_group9.idat.amorecaffeapp.retrofit;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.model.DetallePedido;
import ec2_group9.idat.amorecaffeapp.model.Mesa;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DetallePedidoInterface {

    @GET(Routes.detallePedido)
    public Call<List<DetallePedido>> findAll();

    @GET(Routes.detallePedido +"/{search}")
    public Call<DetallePedido> findOne(@Path("search") String search);

    @POST(Routes.detallePedido +"/create")
    public Call<DetallePedido> create(@Body DetallePedido cliente);

    @PATCH(Routes.detallePedido)
    public Call<DetallePedido> update(@Body DetallePedido cliente);

    @DELETE(Routes.detallePedido +"/{id}")
    public Call<?> delete(@Path("id") String id);

    @POST(Routes.detallePedido +"/createall")
    public Call<List<DetallePedido>> createAll(@Body List<DetallePedido> listDetallePedido);

}
