package imo.moraes.isabele.com.br.colecao;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();

        //handler chama uma thread por tras que nos dara o delay. Runnable é a thread.
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarLista();
            }
        }, 2000);

    }

    private void mostrarLista() {
        Intent intent = new Intent(Splash.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
