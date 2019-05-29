package imo.moraes.isabele.com.br.colecao;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import imo.moraes.isabele.com.br.colecao.model.ActionFigure;
import imo.moraes.isabele.com.br.colecao.model.ActionFigureController;
import imo.moraes.isabele.com.br.colecao.model.AdapterMarcas;
import imo.moraes.isabele.com.br.colecao.model.ListaMarcas;
import imo.moraes.isabele.com.br.colecao.model.Marca;
import imo.moraes.isabele.com.br.colecao.view.DatePickerFragment;

public class Cadastro_item extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    //Componentes
    TextView txtNome;
    TextView txtQtde;
    TextView txtData;
    TextView txtValor;
    TextView txtObservacao;
    Spinner spinnerMarca;
    CheckBox checkPreferido;
    Button btnCadastrar ;

    //Dados
    private int id;
    private String nome;
    private int qtde;
    private Calendar dataCompra;
    private float valor;
    private String observacao;
    private Marca marca;
    private boolean preferido;
    private ListaMarcas listaMarcas;

    //Classes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item);

        //Pegando referencia componentes.
        txtNome = findViewById(R.id.txtNome);
        txtQtde = findViewById(R.id.txtQtde);
        txtData = findViewById(R.id.txtData);
        //xtData.setEnabled(false);
        txtValor = findViewById(R.id.txtValor);
        txtObservacao = findViewById(R.id.txtObservacao);
        spinnerMarca = findViewById(R.id.spinnerMarca);
        checkPreferido = findViewById(R.id.checkPreferido);
        btnCadastrar = findViewById(R.id.btnGravar);

        criaMarcas();

        spinnerMarca.setAdapter (new AdapterMarcas(listaMarcas, this));

        spinnerMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int index, long id) {
                marca = listaMarcas.getMarcas().get(index);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

                if(validaPeenchimento()){
                    ActionFigure actionFigure = new ActionFigure(id, nome, qtde, dataCompra, valor, observacao, preferido, marca);

                    ActionFigureController actionFigureController = new ActionFigureController(getBaseContext());

                    String retornoBanco;

                    if(actionFigure.getId() == 0){
                        retornoBanco = actionFigureController.gravar(actionFigure);
                    }else{
                        retornoBanco = actionFigureController.alterar(actionFigure);
                    }

                    String texto = (retornoBanco+ "\n" +
                            "Nome: "+ nome+
                            "\nQtde: "+ qtde+
                            "\nvalor: "+ valor+
                            "\nObservacao: "+ observacao +
                            "\nMarca: "+ marca.getNome() +
                            "\npreferido: "+ preferido);

                    Toast.makeText(getApplicationContext(),texto, Toast.LENGTH_LONG).show();

                    finish();
                }
            }
        });
    }



    private boolean validaPeenchimento(){
        nome = txtNome.getText().toString();
        String qtdeDoCampo = txtQtde.getText().toString();
        String valorDoCampo = txtValor.getText().toString();

        if(nome.isEmpty() || dataCompra == null){
            Toast.makeText(getApplicationContext(),"Preencha todos os campos obrigatórios", Toast.LENGTH_LONG).show();
            return false;
        }else{
            id = 0;
            qtde = !qtdeDoCampo.isEmpty() ? Integer.parseInt(qtdeDoCampo) : 1;
            valor = !valorDoCampo.isEmpty() ? Float.parseFloat(valorDoCampo) : 0;
            observacao = txtObservacao.getText().toString();
            preferido = checkPreferido.isChecked();
            return true;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

        dataCompra = Calendar.getInstance();
        dataCompra.set(Calendar.YEAR, year);
        dataCompra.set(Calendar.MONTH, month);
        dataCompra.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

        String dataFormatada = formater.format(dataCompra.getTime());

        txtData.setText(dataFormatada);
    }

    private void criaMarcas(){
        //Futuramente esse funcionamento pode ser realizado por uma tela de cadastro de marcas.
        //Nesse momento as marcas estão sendo chumbadas no código pois nao há necessidade de um cadastro.

        List<Marca> marcas = new ArrayList<Marca>();

        marcas.add(new Marca(1,"Marvel"));
        marcas.add(new Marca(2,"DC"));
        marcas.add(new Marca(3,"Games"));
        marcas.add(new Marca(4,"Outros"));

        listaMarcas = new ListaMarcas(marcas);

    }
}
