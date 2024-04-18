package view;

import controller.ControleAtividade;
import controller.ControleProva;
import model.Atividade;
import model.Prova;
import Interface.Avaliacao;




import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Principal extends Application {
    private Avaliacao<Prova> controleProva;
    private Avaliacao<Atividade> controleAtividade;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Avaliações");

        controleProva = new ControleProva();
        controleAtividade = new ControleAtividade();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);

        Label menuLabel = new Label("Menu:");
        grid.add(menuLabel, 0, 0, 2, 1);

        Button cadastrarProvaButton = new Button("Cadastrar Prova");
        grid.add(cadastrarProvaButton, 0, 1);
        cadastrarProvaButton.setOnAction(e -> cadastrarProva(primaryStage));

        Button cadastrarAtividadeButton = new Button("Cadastrar Atividade");
        grid.add(cadastrarAtividadeButton, 1, 1);
        cadastrarAtividadeButton.setOnAction(e -> cadastrarAtividade(primaryStage));

        Button consultarProvaButton = new Button("Consultar Prova");
        grid.add(consultarProvaButton, 0, 2);
        consultarProvaButton.setOnAction(e -> consultarProva(primaryStage));

        Button consultarAtividadeButton = new Button("Consultar Atividade");
        grid.add(consultarAtividadeButton, 1, 2);
        consultarAtividadeButton.setOnAction(e -> consultarAtividade(primaryStage));

        Button limparProvasButton = new Button("Limpar Lista de Provas");
        grid.add(limparProvasButton, 0, 3);
        limparProvasButton.setOnAction(e -> limparProvas());

        Button limparAtividadesButton = new Button("Limpar Lista de Atividades");
        grid.add(limparAtividadesButton, 1, 3);
        limparAtividadesButton.setOnAction(e -> limparAtividades());

        Button sairButton = new Button("Sair");
        grid.add(sairButton, 0, 4, 2, 1);
        sairButton.setOnAction(e -> primaryStage.close());

        primaryStage.show();
    }

    private void cadastrarProva(Stage stage) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Cadastrar Prova");
        dialog.setHeaderText(null);

        Label label1 = new Label("Número de Questões:");
        Label label2 = new Label("Média da Nota:");

        TextField field1 = new TextField();
        TextField field2 = new TextField();

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(field1, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(field2, 2, 2);

        dialog.getDialogPane().setContent(grid);

        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        dialog.setResultConverter(button -> {
            if (button == buttonTypeOk) {
                try {
                    int numQuestoes = Integer.parseInt(field1.getText());
                    double mediaNota = Double.parseDouble(field2.getText());
                    controleProva.cadastrar(new Prova(numQuestoes, mediaNota));
                    return "OK";
                } catch (NumberFormatException e) {
                    exibirErro("Por favor, insira números válidos.");
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void cadastrarAtividade(Stage stage) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Cadastrar Atividade");
        dialog.setHeaderText(null);

        Label label1 = new Label("Assunto:");
        Label label2 = new Label("Número de Páginas:");

        TextField field1 = new TextField();
        TextField field2 = new TextField();

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(field1, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(field2, 2, 2);

        dialog.getDialogPane().setContent(grid);

        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        dialog.setResultConverter(button -> {
            if (button == buttonTypeOk) {
                try {
                    String assunto = field1.getText();
                    int numPaginas = Integer.parseInt(field2.getText());
                    controleAtividade.cadastrar(new Atividade(assunto, numPaginas));
                    return "OK";
                } catch (NumberFormatException e) {
                    exibirErro("Por favor, insira números válidos.");
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void consultarProva(Stage stage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Consultar Prova");
        dialog.setHeaderText(null);
        dialog.setContentText("Posição da Prova:");

        dialog.showAndWait().ifPresent(posStr -> {
            try {
                int posicao = Integer.parseInt(posStr);
                if (posicao >= 0) {
                    Prova prova = controleProva.consultar(posicao);
                    if (prova != null) {
                        exibirInformacao("Prova Encontrada", prova.toString());
                    } else {
                        exibirInformacao("Teste","Não foi encontrada nenhuma prova na posição especificada.Começa com zero");
                    }
                } else {
                    exibirErro("Por favor, insira um número válido para a posição.");
                }
            } catch (NumberFormatException e) {
                exibirErro("Por favor, insira um número válido para a posição.");
            }
        });
    }


    private void consultarAtividade(Stage stage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Consultar Atividade");
        dialog.setHeaderText(null);
        dialog.setContentText("Posição da Atividade:");

        dialog.showAndWait().ifPresent(posStr -> {
            try {
                int posicao = Integer.parseInt(posStr);
                if (posicao >= 0) {
                    Atividade atividade = controleAtividade.consultar(posicao);
                    if (atividade != null) {
                        exibirInformacao("Atividade Encontrada", atividade.toString());
                    } else {
                    	exibirInformacao("teste" ,"Não foi encontrada nenhuma atividade na posição especificada. Começa com zero");
                    }
                } else {
                    exibirErro("Por favor, insira um número válido para a posição.");
                }
            } catch (NumberFormatException e) {
                exibirErro("erro");
            }
        });
    }

    private void limparProvas() {
        controleProva.limparLista();
        exibirInformacao("Lista de Provas Limpa", "A lista de provas foi limpa com sucesso.");
    }

    private void limparAtividades() {
        controleAtividade.limparLista();
        exibirInformacao("Lista de Atividades Limpa", "A lista de atividades foi limpa com sucesso.");
    }

    private void exibirErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void exibirInformacao(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}

