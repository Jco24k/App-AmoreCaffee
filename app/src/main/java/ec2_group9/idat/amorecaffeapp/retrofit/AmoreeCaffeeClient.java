package ec2_group9.idat.amorecaffeapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AmoreeCaffeeClient {


    private static final String BASE_URL = "https://nestjs-amoreecaffee-production.up.railway.app/api/";
    private ClienteInterface clienteInstance;
    private CategoriaInterface categoriaInstance;
    private MesaInterface mesaInstance;
    private ProductoInterface productoInstance;
    private DetallePedidoInterface detPedidoInstance;


    public AmoreeCaffeeClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        clienteInstance = retrofit.create(ClienteInterface.class);
        categoriaInstance = retrofit.create(CategoriaInterface.class);
        mesaInstance = retrofit.create(MesaInterface.class);
        productoInstance = retrofit.create(ProductoInterface.class);
        detPedidoInstance = retrofit.create(DetallePedidoInterface.class);

    }

    public ClienteInterface getClienteInstance() {
        return clienteInstance;
    }

    public CategoriaInterface getCategoriaInstance() {return categoriaInstance;}

    public MesaInterface getMesaInstance() { return mesaInstance; }

    public ProductoInterface getProductoInstance() { return productoInstance; }

    public DetallePedidoInterface getDetPedidoInstance() {
        return detPedidoInstance;
    }
}
