package controller;

import java.util.ArrayList;
import java.util.List;

import Interface.Avaliacao;
import model.Atividade;




public class ControleAtividade implements Avaliacao<Atividade> {
    private ArrayList<Atividade> atividades = new ArrayList<>();

    @Override
    public void cadastrar(Atividade atividade) {
        atividades.add(atividade);
    }

    @Override
    public Atividade consultar(int posicao) {
        if (posicao >= 0 && posicao < atividades.size()) {
            return atividades.get(posicao);
        }
        return null;
    }

    @Override
    public void limparLista() {
        atividades.clear();
    }
}


