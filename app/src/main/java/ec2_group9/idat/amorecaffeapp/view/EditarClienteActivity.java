package ec2_group9.idat.amorecaffeapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.R;
import ec2_group9.idat.amorecaffeapp.databinding.ActivityEditarClienteBinding;
import ec2_group9.idat.amorecaffeapp.global.AuthClienteGlobal;
import ec2_group9.idat.amorecaffeapp.global.CarritoGlobal;
import ec2_group9.idat.amorecaffeapp.model.CabeceraPedido;
import ec2_group9.idat.amorecaffeapp.model.Cliente;
import ec2_group9.idat.amorecaffeapp.view.fragments.MiCuentaFragment;
import ec2_group9.idat.amorecaffeapp.viewModel.ClienteViewModel;

public class EditarClienteActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityEditarClienteBinding binding;
    private ClienteViewModel clienteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditarClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnEditarCliente.setOnClickListener(this);
        binding.btnRegresarMiCuenta.setOnClickListener(this);

        clienteViewModel = new ViewModelProvider(this)
                .get(ClienteViewModel.class);
        clienteViewModel.findOne(AuthClienteGlobal.idCliente);
        clienteViewModel.findOneMutableLiveData.observe(EditarClienteActivity.this, new Observer<Cliente>() {
            @Override
            public void onChanged(Cliente cliente) {
                binding.etnombrenuevo.setText(cliente.getNombre());
                binding.etapellidonuevo.setText(cliente.getApellidos());
                binding.ettelefononuevo.setText(cliente.getTelefono());
            }
        });

        clienteViewModel.updateMutableLiveData.observe(EditarClienteActivity.this, new Observer<Cliente>() {
            @Override
            public void onChanged(Cliente cliente) {
                verificarUpdate(cliente);
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRegresarMiCuenta) {
            onBackPressed();
        } else if (view.getId() == R.id.btnEditarCliente) {
            boolean verificar = validarFormulario();
            if (verificar) {
                Cliente requestcliente = new Cliente(
                        binding.etnombrenuevo.getText().toString(),
                        binding.etapellidonuevo.getText().toString(),
                        binding.ettelefononuevo.getText().toString()
                );
                clienteViewModel.update(requestcliente, AuthClienteGlobal.idCliente);

            }
        }
    }

    private void verificarUpdate(Cliente responseCliente) {
        if(responseCliente!=null){
            MiCuentaFragment.updateCliente(responseCliente);
            finish();
        }else{
            Toast.makeText(this, "Error",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private boolean validarFormulario(){

        List<String> inputsText = Arrays.asList( binding.etnombrenuevo.getText().toString().trim(),binding.etapellidonuevo.getText().toString().trim(),
                binding.ettelefononuevo.getText().toString()
        ),inputs = Arrays.asList("Nombre","Apellido","Telefono","Correo","Contra");
        List<Integer> validInputs = Arrays.asList(1,1,9,5,5 );
        for (int i = 0; i < inputsText.size(); i++) {
            if(inputsText.get(i).length() < validInputs.get(i)){
                Toast.makeText(this,  "Ingresar dato valido: "+inputs.get(i),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if( binding.ettelefononuevo.getText().toString().charAt(0)!= '9'){
            Toast.makeText(this,  "El numero debe empezar con 9",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}