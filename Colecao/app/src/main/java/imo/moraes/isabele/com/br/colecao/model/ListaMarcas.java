package imo.moraes.isabele.com.br.colecao.model;

import java.util.ArrayList;
import java.util.List;

public class ListaMarcas {
    List<Marca> marcas;

    public ListaMarcas() {
        marcas = new ArrayList<Marca>();
    }
    public ListaMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    public void addMarca(Marca marca){
        marcas.add(marca);
    }

    public List<Marca> getMarcas(){
        return marcas;
    }
}
