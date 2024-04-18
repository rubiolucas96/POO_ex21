package controller;

import java.util.ArrayList;
import java.util.List;

import Interface.Avaliacao;
import model.Prova;



public class ControleProva implements Avaliacao<Prova> {
    private ArrayList<Prova> provas = new ArrayList<>();

    @Override
    public void cadastrar(Prova prova) {
        provas.add(prova);
    }

    @Override
    public Prova consultar(int posicao) {
        if (posicao >= 0 && posicao < provas.size()) {
            return provas.get(posicao);
        }
        return null;
    }

    @Override
    public void limparLista() {
        provas.clear();
    }
}

