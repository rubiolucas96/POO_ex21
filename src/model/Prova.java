package model;

import java.util.Random;



public class Prova {
    private int numeroQuestoes;
    private double mediaNota;
    private double numeroAleatorio;

    public Prova(int numeroQuestoes, double mediaNota) {
        this.numeroQuestoes = numeroQuestoes;
        this.mediaNota = mediaNota;
        this.numeroAleatorio = new Random().nextDouble() * 10.0; // Gerando número aleatório
    }

    @Override
    public String toString() {
        return "Prova: Número de Questões - " + numeroQuestoes + ", Média da Nota - " + mediaNota + ", Número Aleatório - " + numeroAleatorio;
    }
}
