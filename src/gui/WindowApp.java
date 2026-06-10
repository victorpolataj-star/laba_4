package gui;

import core.DataProcessor;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class WindowApp extends Application {

    private DataProcessor processor;
    private TextArea inputArea;
    private ListView<String> resultList;
    private Stage resultStage;

    @Override
    public void start(Stage primaryStage) {
        processor = new DataProcessor();

        // Главное окно с вводом данных
        primaryStage.setTitle("Ввод данных");
        primaryStage.setWidth(500);
        primaryStage.setHeight(400);

        inputArea = new TextArea();
        inputArea.setPromptText("Введите числа, каждое на новой строке\n\nПример:\n9\n-1\n3\n7\n-5");

        Button processBtn = new Button("ОБРАБОТАТЬ");
        processBtn.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        processBtn.setOnAction(e -> showResultInNewWindow());

        Button exampleBtn = new Button("ЗАГРУЗИТЬ ПРИМЕР");
        exampleBtn.setOnAction(e -> loadExample());

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(
                new Label("📝 ВВОД ДАННЫХ:"),
                inputArea,
                processBtn,
                exampleBtn
        );

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void showResultInNewWindow() {
        String text = inputArea.getText();
        if (text == null || text.trim().isEmpty()) {
            showAlert("Ошибка", "Введите данные!");
            return;
        }

        String[] lines = text.split("\\r?\\n");
        String[] result = processor.processPipeline(lines);

        // Создаем НОВОЕ ОКНО для результата
        resultStage = new Stage();
        resultStage.setTitle("📊 РЕЗУЛЬТАТ ОБРАБОТКИ");
        resultStage.setWidth(400);
        resultStage.setHeight(500);

        // Список с результатами
        ListView<String> resultView = new ListView<>();
        for (String r : result) {
            resultView.getItems().add(r);
        }

        // Кнопка закрытия
        Button closeBtn = new Button("ЗАКРЫТЬ");
        closeBtn.setOnAction(e -> resultStage.close());

        // Кнопка копирования
        Button copyBtn = new Button("КОПИРОВАТЬ");
        copyBtn.setOnAction(e -> {
            String allResults = String.join("\n", result);
            javafx.scene.input.Clipboard clipboard =
                    javafx.scene.input.Clipboard.getSystemClipboard();
            javafx.scene.input.ClipboardContent content =
                    new javafx.scene.input.ClipboardContent();
            content.putString(allResults);
            clipboard.setContent(content);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Скопировано");
            alert.setHeaderText(null);
            alert.setContentText("Результат скопирован в буфер обмена!");
            alert.showAndWait();
        });

        HBox buttonPanel = new HBox(10, copyBtn, closeBtn);
        buttonPanel.setPadding(new Insets(10));

        VBox resultRoot = new VBox(10);
        resultRoot.setPadding(new Insets(10));
        resultRoot.getChildren().addAll(
                new Label("✅ ОТРИЦАТЕЛЬНЫЕ ЧИСЛА ПЕРЕМЕЩЕНЫ В НАЧАЛО:"),
                new Label("Всего чисел: " + result.length),
                new Separator(),
                resultView,
                buttonPanel
        );

        resultStage.setScene(new Scene(resultRoot));
        resultStage.show();
    }

    private void loadExample() {
        inputArea.setText("9\n-1\n3\n7\n-5\n-7\n1\n3\n-3\n2");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}