package imo.moraes.isabele.com.br.colecao;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.time.LocalDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import imo.moraes.isabele.com.br.colecao.view.DatePickerFragment;

public class Cadastro_item extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    String[] SPINNERLIST = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};

    //Componentes
    TextView txtNome;
    TextView txtQtde;
    TextView txtData;
    TextView txtValor;
    TextView txtObservacao;
    MaterialBetterSpinner spinnerMarca;
    CheckBox checkPreferido;
    Button btnCadastrar ;

    //Dados
    private String nome;
    private int qtde;
    private LocalDate dataCompra;
    private float valor;
    private String observacao;
    private String marca;
    private boolean preferido;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item);

        //Pegando referencia componentes.
        txtNome = findViewById(R.id.txtNome);
        txtQtde = findViewById(R.id.txtQtde);
        txtData = findViewById(R.id.txtData);
        txtValor = findViewById(R.id.txtValor);
        txtObservacao = findViewById(R.id.txtObservacao);
        spinnerMarca = findViewById(R.id.spinnerMarca);
        checkPreferido = findViewById(R.id.checkPreferido);
        btnCadastrar = findViewById(R.id.btnGravar);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);

        spinnerMarca.setAdapter(arrayAdapter);

        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaPeenchimento();

                String texto = ("Nome: "+ nome+
                        "\nQtde: "+ qtde+
                        "\nvalor: "+ valor+
                        "\nObservacao: "+ observacao +
                        "\nMarca: "+ marca +
                        "\npreferido: "+ preferido);

                Toast.makeText(getApplicationContext(),texto, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void validaPeenchimento(){
        nome = txtNome.getText().toString();
        String qtdeDoCampo = txtQtde.getText().toString();
        String dataCompraDoCampo = txtData.getText().toString();
        String valorDoCampo = txtValor.getText().toString();

        if(nome.isEmpty() || dataCompraDoCampo.isEmpty()){
            Toast.makeText(getApplicationContext(),"Preencha todos os campos obrigatórios", Toast.LENGTH_LONG).show();
        }else{
            nome =  txtNome.getText().toString();
            qtde = !qtdeDoCampo.isEmpty() ? Integer.parseInt(qtdeDoCampo) : 1;
            //dataCompra =
            valor = !valorDoCampo.isEmpty() ? Float.parseFloat(valorDoCampo) : 0;
            observacao = txtObservacao.getText().toString();
            marca = spinnerMarca.getText().toString();
            preferido = checkPreferido.isChecked();
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }
}
