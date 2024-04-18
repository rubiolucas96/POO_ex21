package model;

public class Atividade {
    private String assunto;
    private int numeroPaginas;

    public Atividade(String assunto, int numeroPaginas) {
        this.assunto = assunto;
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String toString() {
        return "Atividade: Assunto - " + assunto + ", Número de Páginas - " + numeroPaginas;
    }
}
