package imo.moraes.isabele.com.br.colecao.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import imo.moraes.isabele.com.br.colecao.dao.CriaBanco;

public class ActionFigureController {
    private static final String NOME_TABELA = "action_figure";
    private ContentValues valores;
    private long retornoOperacao;
    private CriaBanco banco;

    public ActionFigureController(Context context) {
        banco = new CriaBanco(context);
    }

    public String gravar(ActionFigure actionFigure){
        valores = new ContentValues();
        valores.put("_id", actionFigure.getId());
        extrairDados(actionFigure);
        retornoOperacao = banco.getWritableDatabase().insert(NOME_TABELA,null,valores);

        //criar forma de retornar o id gerado

        return verificaSucesso("gravar");
    }

    public String alterar(ActionFigure actionFigure){
        valores = new ContentValues();
        extrairDados(actionFigure);
        String[] parametros = {String.valueOf(actionFigure.getId())};
        retornoOperacao =banco.getWritableDatabase().update(NOME_TABELA,valores,"_id=?",parametros);
        return verificaSucesso("alterar");
    }

    public String deletar(ActionFigure actionFigure){
        SQLiteDatabase db = banco.getWritableDatabase();
        String[] parametros = {String.valueOf(actionFigure.getId())};
        retornoOperacao = banco.getWritableDatabase().delete(NOME_TABELA,"id_item=?",parametros);
        return verificaSucesso("deletar");
    }

    public List<ActionFigure> buscarTodos(){
        SQLiteDatabase db = banco.getWritableDatabase();

        Cursor c = db.rawQuery("select * from action_figure;", null);

        List<ActionFigure> listaActionFigure = new ArrayList<ActionFigure>();

        while(c.moveToNext()){

            int id = c.getInt(c.getColumnIndex("_id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            int qtde = c.getInt(c.getColumnIndex("quantidade"));
            Calendar dataCompra = transformaData(c.getString(c.getColumnIndex("dt_compra")));
            float valor = c.getFloat(c.getColumnIndex("valor"));
            String observacao = c.getString(c.getColumnIndex("observacao"));
            boolean preferido = c.getInt(c.getColumnIndex("preferido")) == 1?true:false;
            Marca marca = buscaMarca(c.getInt(c.getColumnIndex("marca")));

            ActionFigure actionFigure = new ActionFigure(id,nome,qtde,dataCompra,valor,observacao,preferido,marca);

            listaActionFigure.add(actionFigure);
        }

        c.close();

        return listaActionFigure;
    }

    public void extrairDados(ActionFigure actionFigure){
        valores.put("nome",actionFigure.getNome());
        valores.put("quantidade",actionFigure.getQtde());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        valores.put("dt_compra",format.format(actionFigure.getDataCompra().getTime()));

        valores.put("valor",actionFigure.getValor());
        valores.put("observacao",actionFigure.getObservacao());

        int valorPreferido= actionFigure.isPreferido()?1:0;
        valores.put("preferido",valorPreferido);

        valores.put("marca",actionFigure.getMarca().getId());
    }

    public String verificaSucesso(String operacao){
        if(retornoOperacao == -1){
            return "ERRO ao "+operacao+" action figure";
        }else{
            return "Sucesso ao "+operacao+" action figure";
        }
    }

    private Calendar transformaData(String data){
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(data));
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Marca buscaMarca(int idMarca){
        // Esse metodo não seria necessario se tivessemos um cadastro de marcas, pois bastaria chamar a classe de MarcaController para buscar uma marca especifica.
        // No momento não há necessidade de um cadastro de marcas.

        Marca marca = null;

        switch (idMarca){
            case 1:
                marca = new Marca(1,"Marvel");
                break;
            case 2:
                marca = new Marca(2,"DC");
                break;
            case 3:
                marca = new Marca(3,"Games");
                break;
            case 4:
                marca = new Marca(4,"Outros");
                break;
        }
        return marca;
    }
}
