package Interface;

public interface Avaliacao<T> {
    void cadastrar(T atividade);
    T consultar(int posicao);
    void limparLista();
}
