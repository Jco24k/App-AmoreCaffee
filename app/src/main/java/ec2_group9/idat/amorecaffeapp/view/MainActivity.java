package ec2_group9.idat.amorecaffeapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import ec2_group9.idat.amorecaffeapp.R;
import ec2_group9.idat.amorecaffeapp.databinding.ActivityMainBinding;
import ec2_group9.idat.amorecaffeapp.global.AuthClienteGlobal;
import ec2_group9.idat.amorecaffeapp.model.Auth;
import ec2_group9.idat.amorecaffeapp.viewModel.AuthViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding binding;
    private AuthViewModel authViewModel;
    private static String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnlogin.setOnClickListener(this);
        binding.btnRegistrarCliente.setOnClickListener(this);
        authViewModel = new ViewModelProvider(this)
                .get(AuthViewModel.class);
        authViewModel.loginMutableLiveData.observe(this,
                new Observer<Auth>() {
                    @Override
                    public void onChanged(Auth responseLogin) {
                        validarAutenticacion(responseLogin);
                    }
                });
    }

    private void validarAutenticacion(Auth responseLogin) {
        if(responseLogin!=null){
            AuthClienteGlobal.token = responseLogin.getToken();
            AuthClienteGlobal.idCliente = responseLogin.getId();
            iniciarMenu();
        }else{
            Toast.makeText(this, "Correo o Password incorrecto",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnlogin){
            autenticarCliente();
        }else if(v.getId() == R.id.btnRegistrarCliente){
            Intent intentClient = new Intent(MainActivity.this, RegistroClienteActivity.class);
            startActivity(intentClient);
        }
    }

    public void autenticarCliente(){
        Auth requestLogin = new Auth();
        requestLogin.setCorreo(binding.etusuario.getText().toString());
        requestLogin.setPassword(binding.etpassword.getText().toString());
        authViewModel.login(requestLogin);
    }

    private void iniciarMenu(){
        Intent intentLista = new Intent(MainActivity.this,MenuActivity.class);
        startActivity(intentLista);
    }
}