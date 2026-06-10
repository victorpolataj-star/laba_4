package gui;

import core.DataProcessor;
import javax.swing.*;
import java.awt.*;

public class SimpleResultWindow {

    public static void showResult(String[] input, String[] result) {
        // Создаем отдельное окно
        JFrame resultFrame = new JFrame("📊 РЕЗУЛЬТАТ ОБРАБОТКИ");
        resultFrame.setSize(500, 600);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Текст с результатом
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        StringBuilder sb = new StringBuilder();
        sb.append("╔══════════════════════════════════════════════════╗\n");
        sb.append("║              РЕЗУЛЬТАТ ОБРАБОТКИ                 ║\n");
        sb.append("║         Вариант 1: Отрицательные в начало        ║\n");
        sb.append("╚══════════════════════════════════════════════════╝\n\n");

        sb.append("📥 ИСХОДНЫЕ ДАННЫЕ:\n");
        sb.append("─".repeat(50)).append("\n");
        for (int i = 0; i < input.length; i++) {
            sb.append(String.format("%3d | %s\n", i+1, input[i]));
        }

        sb.append("\n📤 РЕЗУЛЬТАТ:\n");
        sb.append("─".repeat(50)).append("\n");
        for (int i = 0; i < result.length; i++) {
            sb.append(String.format("%3d | %s\n", i+1, result[i]));
        }

        sb.append("\n✅ Отрицательные числа перемещены в начало!");
        sb.append("\n   Порядок внутри групп сохранен.");

        textArea.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        resultFrame.add(scrollPane);

        // Кнопка закрытия
        JButton closeBtn = new JButton("ЗАКРЫТЬ");
        closeBtn.addActionListener(e -> resultFrame.dispose());
        resultFrame.add(closeBtn, BorderLayout.SOUTH);

        resultFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // Пример использования
        DataProcessor processor = new DataProcessor();
        String[] input = {"9", "-1", "3", "7", "-5", "-7", "1", "3", "-3", "2"};
        String[] result = processor.processPipeline(input);
        showResult(input, result);
    }
}