package imo.moraes.isabele.com.br.colecao.model;

import java.time.LocalDate;
import java.util.Calendar;

public class ActionFigure extends Item {

    private Marca marca;

    public ActionFigure(int id, String nome, int qtde, Calendar dataCompra, float valor, String observacao, boolean preferido, Marca marca) {
        super(id, nome, qtde, dataCompra, valor, observacao, preferido);
        this.marca = marca;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
