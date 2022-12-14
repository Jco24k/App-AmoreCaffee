package ec2_group9.idat.amorecaffeapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ec2_group9.idat.amorecaffeapp.databinding.ActivityPagoBinding;

public class PagoActivity extends AppCompatActivity {

    private ActivityPagoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}