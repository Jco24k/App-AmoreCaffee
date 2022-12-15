package ec2_group9.idat.amorecaffeapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import ec2_group9.idat.amorecaffeapp.R;
import ec2_group9.idat.amorecaffeapp.databinding.ActivityPagoBinding;
import ec2_group9.idat.amorecaffeapp.global.AuthClienteGlobal;
import ec2_group9.idat.amorecaffeapp.global.CarritoGlobal;
import ec2_group9.idat.amorecaffeapp.model.CabeceraPedido;
import ec2_group9.idat.amorecaffeapp.model.Cliente;
import ec2_group9.idat.amorecaffeapp.model.DetallePedido;
import ec2_group9.idat.amorecaffeapp.model.Mesa;
import ec2_group9.idat.amorecaffeapp.view.fragments.MenuFragment;
import ec2_group9.idat.amorecaffeapp.viewModel.CabeceraPedidoViewModel;
import ec2_group9.idat.amorecaffeapp.viewModel.ClienteViewModel;
import ec2_group9.idat.amorecaffeapp.viewModel.DetallePedidoViewModel;
import ec2_group9.idat.amorecaffeapp.viewModel.MesaViewModel;

public class PagoActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityPagoBinding binding;
    private ClienteViewModel clienteViewModel;
    private CabeceraPedidoViewModel cabeceraPedidoViewModel;
    private MesaViewModel mesaViewModel;
    private DetallePedidoViewModel detallePedidoViewModel;

    private Cliente clienteEntity = null;
    private Mesa mesaEntity = null;
    private CabeceraPedido cabeceraPedidoEntity = null;
    private List<DetallePedido> listDetallePedidosEntity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        clienteViewModel = new ViewModelProvider(this)
                .get(ClienteViewModel.class);
        clienteViewModel.findOne(AuthClienteGlobal.idCliente);
        clienteViewModel.findOneMutableLiveData.observe(this, new Observer<Cliente>() {
            @Override
            public void onChanged(Cliente cliente) {
                clienteEntity = cliente;
                binding.txtNombrePago.setText(clienteEntity.getNombre());
                int indexSelection = String.valueOf(binding.txtNombrePago.getText()).length();
                binding.txtNombrePago.setSelection(indexSelection);
            }
        });
        binding.txtMesa.setText("");
        binding.txtMesa.setEnabled(false);
        binding.rbtLlevar.setOnClickListener(this);
        binding.rbtMesa.setOnClickListener(this);
        binding.btnRegistrarVenta.setOnClickListener(this);
        binding.btnRegresarCarrito.setOnClickListener(this);

        cabeceraPedidoViewModel = new ViewModelProvider(this)
                .get(CabeceraPedidoViewModel.class);
        cabeceraPedidoViewModel.registroMutableLiveData.observe(this,
                new Observer<CabeceraPedido>() {
                    @Override
                    public void onChanged(CabeceraPedido responseCab) {
                        validarCabPedidoExist(responseCab);
                    }
                });


        detallePedidoViewModel = new ViewModelProvider(this)
                .get(DetallePedidoViewModel.class);
        detallePedidoViewModel.registroMutableLiveData.observe(this,
                new Observer<List<DetallePedido>>() {
                    @Override
                    public void onChanged(List<DetallePedido> responseDetalle) {
                        validarDetPedidoExist(responseDetalle);
                    }
                });
    }

    private void validarCabPedidoExist(CabeceraPedido responseCab) {
        if(responseCab!=null){
            cabeceraPedidoEntity = responseCab;
            CarritoGlobal.addCabeceraPedido(cabeceraPedidoEntity);
            detallePedidoViewModel.registrarAllDetallePedido(CarritoGlobal.listaDetallePedido);
        }else{
            Toast.makeText(this, "Mesa no encontrada",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void validarDetPedidoExist(List<DetallePedido> responseDetalle) {
        if(responseDetalle!=null){
            Toast.makeText(this, "Pedido realizado con exito",
                    Toast.LENGTH_SHORT).show();
            cargarMenu();
        }else{
            Toast.makeText(this, "Error al Registrar DetallePedido",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private Boolean validateForm(){
        boolean respuesta = true;
        if(binding.rgMetodosPago.getCheckedRadioButtonId() == -1){
            respuesta = false;
        }
        if(binding.rgTipoPedido.getCheckedRadioButtonId() == -1){
            respuesta = false;
        }
        if(binding.rbtMesa.isChecked() && binding.txtMesa.getText().toString().trim().length()==0){
            respuesta = false;
        }
        if(binding.txtNombrePago.getText().length() == 0){
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rbtLlevar) {
            binding.txtMesa.setText("");
            binding.txtMesa.setEnabled(false);
        }else if(v.getId() == R.id.rbtMesa){
            binding.txtMesa.setEnabled(true);
        }else if(v.getId() == R.id.btnRegistrarVenta){
            boolean validar = validateForm();
            if(!validar) Toast.makeText(this, "Completar el formulario correctamente",Toast.LENGTH_SHORT).show();
            else{
                CabeceraPedido cabeceraPedido = null;
                if(binding.rbtMesa.isChecked()){
                    Mesa mesa = new Mesa(binding.txtMesa.getText().toString());
                    cabeceraPedido= new CabeceraPedido(
                            getFormaPagoSelect(),binding.txtNombrePago.getText().toString(),calcularTotal(),
                            getTipoPedidoSelect(),clienteEntity, mesa
                    );
                }else{
                    cabeceraPedido= new CabeceraPedido(
                            getFormaPagoSelect(),binding.txtNombrePago.getText().toString(),calcularTotal(),
                            getTipoPedidoSelect(),clienteEntity
                    );
                }
                cabeceraPedidoViewModel.create(cabeceraPedido);

            }
        }else{
            onBackPressed();
        }
    }

    private double calcularTotal(){
        double total = 0;
        for (DetallePedido det:
                CarritoGlobal.listaDetallePedido) {
            total += det.getSubtotal();
        }
        return total;
    }

    private String getTipoPedidoSelect(){
        String selected = "";
        switch (binding.rgTipoPedido.getCheckedRadioButtonId()){
            case R.id.rbtLlevar:
                selected = "llevar".toLowerCase(); break;
            case R.id.rbtMesa:
                selected = "mesa".toLowerCase(); break;
        }
        return selected;
    }

    private String getFormaPagoSelect(){
        String selected = "";
        switch (binding.rgMetodosPago.getCheckedRadioButtonId()){
            case R.id.rbtBcp:
                selected = binding.rbtBcp.getText().toString().toLowerCase(); break;
            case R.id.rbtYape:
                selected = binding.rbtYape.getText().toString().toLowerCase(); break;
            case R.id.rbtEfectivo:
                selected = binding.rbtEfectivo.getText().toString().toLowerCase(); break;
        }
        return selected;
    }

    private void cargarMenu(){
        CarritoGlobal.iniciarCarrito();
        Intent intentClient = new Intent(PagoActivity.this, MenuActivity.class);
        startActivity(intentClient);
        finish();
    }
}