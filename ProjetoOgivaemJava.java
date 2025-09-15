import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;        
import javafx.scene.image.ImageView;  
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;   
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;          
import javafx.geometry.Pos;           

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class App extends Application {

    private List<Double> dados = new ArrayList<>();
    private LineChart<Number, Number> lineChart;
    private ListView<String> listaDados;

    @Override
    public void start(Stage palco) {
        palco.setTitle("Gráfico de Ogiva Interativo");

        // --- Tela Inicial ---
        ImageView imgView = null;
        try {
            // Tenta carregar a imagem de fundo
            Image imgFundo = new Image("file:grafico.png");
            imgView = new ImageView(imgFundo);
            imgView.setFitWidth(800);
            imgView.setFitHeight(600);
            imgView.setPreserveRatio(false);
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem 'grafico.png'. Certifique-se de que o ficheiro existe.");
            // Se a imagem não for encontrada, o fundo será branco, mas a aplicação continuará.
        }

        // Conteúdo da tela inicial
        VBox conteudo = new VBox(20);
        conteudo.setAlignment(Pos.CENTER); // <-- MELHORIA: Centraliza o conteúdo
        Label lblBemVindo = new Label("Bem-vindo ao aplicativo de Gráfico de Ogiva!");
        lblBemVindo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.5); -fx-padding: 10px;");
        Button btnIniciar = new Button("Iniciar");
        btnIniciar.setStyle("-fx-font-size: 14px;");
        conteudo.getChildren().addAll(lblBemVindo, btnIniciar);

        // StackPane para sobrepor conteúdo à imagem
        StackPane telaInicial = new StackPane();
        if (imgView != null) {
            telaInicial.getChildren().add(imgView);
        }
        telaInicial.getChildren().add(conteudo);

        Scene cenaInicial = new Scene(telaInicial, 800, 600);

        btnIniciar.setOnAction(e -> mostrarTelaPrincipal(palco));

        palco.setScene(cenaInicial);
        palco.show();
    }

    private void mostrarTelaPrincipal(Stage palco) {
        // Campo de entrada de dados
        TextField campoNumero = new TextField();
        campoNumero.setPromptText("Digite um número");
        Button btnAdicionar = new Button("Adicionar");
        Button btnRemover = new Button("Remover Selecionado");
        Button btnLimpar = new Button("Limpar Tudo"); // <-- MELHORIA: Novo botão

        listaDados = new ListView<>();
        listaDados.setPrefHeight(150); // Aumentei um pouco a altura

        // Ação do botão Adicionar
        btnAdicionar.setOnAction(e -> {
            try {
                double valor = Double.parseDouble(campoNumero.getText().trim());
                dados.add(valor);
                atualizarLista();
                atualizarGrafico();
                campoNumero.clear();
            } catch (NumberFormatException ex) {
                // Não faz nada se o texto não for um número válido
                campoNumero.clear();
            }
        });

        // Ação do botão Remover
        btnRemover.setOnAction(e -> {
            int idx = listaDados.getSelectionModel().getSelectedIndex();
            if (idx >= 0) {
                dados.remove(idx);
                atualizarLista();
                atualizarGrafico();
            }
        });

        // Ação do botão Limpar Tudo (NOVO)
        btnLimpar.setOnAction(e -> {
            dados.clear();
            atualizarLista();
            atualizarGrafico();
        });

        HBox entradaBox = new HBox(10, new Label("Número:"), campoNumero, btnAdicionar, btnRemover, btnLimpar);
        entradaBox.setAlignment(Pos.CENTER_LEFT);

        // Eixos do gráfico
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Valores dos Dados");
        yAxis.setLabel("Frequência Acumulada (%)"); 

        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Gráfico de Ogiva - Frequência Acumulada");
        lineChart.setPrefHeight(450);
        lineChart.setCreateSymbols(true); 

        VBox vbox = new VBox(15, entradaBox, listaDados, lineChart);
        vbox.setPadding(new Insets(15)); 

        Scene cena = new Scene(vbox, 800, 600);
        palco.setScene(cena);
    }

    private void atualizarLista() {
        listaDados.getItems().clear();
        Collections.sort(dados); // Ordena a lista de dados antes de exibir
        for (Double d : dados) {
            listaDados.getItems().add(String.valueOf(d));
        }
    }

    private void atualizarGrafico() {
        lineChart.getData().clear();
        if (dados.isEmpty()) return;

        List<Double> dadosOrdenados = new ArrayList<>(dados);
        Collections.sort(dadosOrdenados);

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Frequência Acumulada");

        // MELHORIA: Adiciona um ponto inicial na origem para o gráfico começar do eixo X
        if (!dadosOrdenados.isEmpty()) {
            double primeiroValor = dadosOrdenados.get(0);
            series.getData().add(new XYChart.Data<>(primeiroValor > 0 ? primeiroValor * 0.9 : primeiroValor - 1, 0));
        }

        int totalDados = dadosOrdenados.size();
        for (int i = 0; i < totalDados; i++) {
            double valor = dadosOrdenados.get(i);
            double freqAcumuladaPercentual = ((double) (i + 1) / totalDados) * 100.0;
            series.getData().add(new XYChart.Data<>(valor, freqAcumuladaPercentual));
        }

        lineChart.getData().add(series);
    }

    public static void main(String[] args) {
        launch(args);
    }
}