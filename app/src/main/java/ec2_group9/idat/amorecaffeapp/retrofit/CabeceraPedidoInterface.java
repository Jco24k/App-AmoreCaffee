package ec2_group9.idat.amorecaffeapp.retrofit;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.model.CabeceraPedido;
import ec2_group9.idat.amorecaffeapp.model.Categoria;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CabeceraPedidoInterface {


    @GET(Routes.cabeceraPedido)
    public Call<List<CabeceraPedido>> findAll();

    @GET(Routes.cabeceraPedido +"/{search}")
    public Call<CabeceraPedido> findOne(@Path("search") String search);

    @POST(Routes.cabeceraPedido +"/create")
    public Call<CabeceraPedido> create(@Body CabeceraPedido categoria);

    @PATCH(Routes.cabeceraPedido)
    public Call<CabeceraPedido> update(@Body CabeceraPedido categoria);

    @DELETE(Routes.cabeceraPedido +"/{id}")
    public Call<?> delete(@Path("id") String id);



}
