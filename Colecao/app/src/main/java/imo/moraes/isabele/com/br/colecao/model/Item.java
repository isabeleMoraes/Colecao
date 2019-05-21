package imo.moraes.isabele.com.br.colecao.model;

import java.time.LocalDate;

public abstract class Item {
    private String nome;
    private int qtde;
    private LocalDate dataCompra;
    private float valor;
    private String observacao;
    private boolean preferido;

    public Item(String nome, int qtde, LocalDate dataCompra, float valor, String observacao, boolean preferido) {
        setNome(nome);
        setQtde(qtde);
        setDataCompra(dataCompra);
        setValor(valor);
        setObservacao(observacao);
        setPreferido(preferido);
    }
    public Item(String nome, LocalDate dataCompra,boolean preferido) {
        setNome(nome);
        setQtde(1);
        setDataCompra(dataCompra);
        setPreferido(preferido);
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

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
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
