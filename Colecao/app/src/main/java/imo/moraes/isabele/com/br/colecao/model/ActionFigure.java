package imo.moraes.isabele.com.br.colecao.model;

import java.time.LocalDate;

public class ActionFigure extends Item {

    private int marca;

    public ActionFigure(String nome, LocalDate dataCompra, boolean preferido) {
        super(nome, dataCompra, preferido);
    }

    public ActionFigure(String nome, int qtde, LocalDate dataCompra, float valor, String observacao, boolean preferido) {
        super(nome, qtde, dataCompra, valor, observacao, preferido);
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }
}
