package imo.moraes.isabele.com.br.colecao.model;

import java.time.LocalDate;
import java.util.Calendar;

public abstract class Item {

    private int id;
    private String nome;
    private int qtde;
    private Calendar dataCompra;
    private float valor;
    private String observacao;
    private boolean preferido;

    public Item(int id, String nome, int qtde, Calendar dataCompra, float valor, String observacao, boolean preferido) {
        setNome(nome);
        setQtde(qtde);
        setDataCompra(dataCompra);
        setValor(valor);
        setObservacao(observacao);
        setPreferido(preferido);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public Calendar getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isPreferido() {
        return preferido;
    }

    public void setPreferido(boolean preferido) {
        this.preferido = preferido;
    }
}
