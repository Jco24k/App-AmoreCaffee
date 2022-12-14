package ec2_group9.idat.amorecaffeapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.R;
import ec2_group9.idat.amorecaffeapp.databinding.ActivityPagoBinding;
import ec2_group9.idat.amorecaffeapp.databinding.ActivityRegistroClienteBinding;
import ec2_group9.idat.amorecaffeapp.model.Auth;
import ec2_group9.idat.amorecaffeapp.model.Cliente;
import ec2_group9.idat.amorecaffeapp.viewModel.AuthViewModel;
import ec2_group9.idat.amorecaffeapp.viewModel.ClienteViewModel;

public class RegistroClienteActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegistroClienteBinding binding;
    private ClienteViewModel clienteViewModel;
    private String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnRegresarLogin.setOnClickListener(this);
        binding.btnRegistrarse.setOnClickListener(this);
        clienteViewModel = new ViewModelProvider(this)
                .get(ClienteViewModel.class);
        clienteViewModel.registroMutableLiveData.observe(this,
                new Observer<Cliente>() {
                    @Override
                    public void onChanged(Cliente responseCliente) {
                        validarRegistro(responseCliente);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnRegresarLogin){
            Intent intentClient = new Intent(RegistroClienteActivity.this,MainActivity.class);
            startActivity(intentClient);
            finish();
        }else if( v.getId() == R.id.btnRegistrarse){
                boolean verificar = validarFormulario();
                if(verificar){
                    Cliente requestRegistro = new Cliente(
                            binding.txtRegisterNombre.getText().toString(),
                            binding.txtRegisterApellido.getText().toString(),
                            binding.txtRegisterTelefono.getText().toString(),
                            binding.txtRegisterCorreo.getText().toString(),
                            binding.txtRegisterContra.getText().toString()
                    );
                    clienteViewModel.create(requestRegistro);
                }
            }

    }


    private boolean validarFormulario(){

        List<String> inputsText = Arrays.asList( binding.txtRegisterNombre.getText().toString().trim(),binding.txtRegisterApellido.getText().toString().trim(),
                binding.txtRegisterTelefono.getText().toString(), binding.txtRegisterCorreo.getText().toString(), binding.txtRegisterContra.getText().toString()
        ),inputs = Arrays.asList("Nombre","Apellido","Telefono","Correo","Contra");
        List<Integer> validInputs = Arrays.asList(1,1,9,5,5 );
        for (int i = 0; i < inputsText.size(); i++) {
            if(inputsText.get(i).length() < validInputs.get(i)){
                Toast.makeText(this,  "Ingresar dato valido: "+inputs.get(i),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        String contrasenia = binding.txtRegisterContra.getText().toString(),
                confContra = binding.txtRegisterContraConfir.getText().toString();
        if( !contrasenia.equals(confContra)){
            Toast.makeText(this,  "Confirmacion de contraseña no es igual",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if( !binding.txtRegisterCorreo.getText().toString().contains("@")){
            Toast.makeText(this,  "No es un correo valido",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if( binding.txtRegisterTelefono.getText().toString().charAt(0)!= '9'){
            Toast.makeText(this,  "El numero debe empezar con 9",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private void validarRegistro(Cliente responseCliente) {
        if(responseCliente!=null){
            token = responseCliente.getToken();
            Intent intentClient = new Intent(RegistroClienteActivity.this,MenuActivity.class);
            startActivity(intentClient);
            finish();
        }else{
            Toast.makeText(this, "Error al registrar",
                    Toast.LENGTH_LONG).show();
        }
    }
}