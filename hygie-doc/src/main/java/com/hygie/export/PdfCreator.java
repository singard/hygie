package com.hygie.export;

import com.hygie.hygietask.model.ResultTask;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.util.Matrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PdfCreator {
    private final String title;
    private final String filePath;
    private final InputStream inputStream;

    public PdfCreator(String title, String filePath) throws IOException {
        this.title = title;
        this.filePath = filePath;
        String fontPath = "C:\\WINDOWS\\FONTS\\arial.ttf";
        File fontFile = new File(fontPath);
        this.inputStream = new FileInputStream(fontFile);
    }

    public void createPdf(List<ResultTask> tasks) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float fontSize = 12f;
            float leading = 14f; // Espacement vertical entre les lignes
            float margin = 50f; // Marge gauche
            float yPosition = page.getMediaBox().getHeight() - margin - leading; // Position verticale initiale

            PDType0Font font = PDType0Font.load(document, inputStream);
            contentStream.setFont(font, fontSize);

            String boldFontPath = "C:\\WINDOWS\\FONTS\\arialbd.ttf";
            File boldFontFile = new File(boldFontPath);
            InputStream boldInputStream = new FileInputStream(boldFontFile);
            PDType0Font boldFont = PDType0Font.load(document, boldInputStream);

            contentStream.setFont(boldFont, 24f); // Spécifiez la taille de la police du titre en gras

            String testTypeLabel = "Test effectué:";
            String descriptionLabel = "Description:";
            String successfulTestLabel = "Réussite du test:";
            String resultLabel = "Rapport:";

            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition); // Position initiale du titre

            float titleWidth = boldFont.getStringWidth(title) / 1000 * 18f;
            float titleX = (page.getMediaBox().getWidth() - titleWidth) / 2; // Calculer la position x centrée

            contentStream.setTextMatrix(Matrix.getTranslateInstance(titleX, yPosition));
            contentStream.showText(title); // Affiche le titre en gras
            contentStream.endText();

            yPosition -= leading; // Décale la position verticale pour le contenu suivant

            // Dessiner une ligne
            contentStream.setLineWidth(1f);
            contentStream.moveTo(margin, yPosition);
            contentStream.lineTo(page.getMediaBox().getWidth() - margin, yPosition);
            contentStream.stroke();

            yPosition -= 3 * leading; // Décale la position verticale pour le contenu suivant


            for (ResultTask task : tasks) {
                contentStream.setFont(font, fontSize); // Utiliser la police normale pour le texte principal

                String testType = task.getExecuteTask().getTestType().toString();
                String description = task.getExecuteTask().getDescription();
                boolean successfulTest = task.isSuccessfulTest();
                String result = task.getResult();

                String formattedResult = testTypeLabel + " " + testType + "\n" +
                        descriptionLabel + " " + description + "\n" +
                        successfulTestLabel + " " + successfulTest + "\n" +
                        resultLabel + " " + result + "\n\n";

                formattedResult = replaceUnsupportedCharacters(formattedResult); // Remplace les caractères non pris en charge

                // Split the formatted result into lines
                String[] lines = formattedResult.split("\n");

                contentStream.beginText(); // Début du flux de texte pour chaque tâche
                contentStream.setFont(font, fontSize); // Utiliser la police normale pour le texte principal

                contentStream.newLineAtOffset(margin, yPosition); // Position initiale du texte

                for (String line : lines) {
                    String[] parts = line.split(": ", 2); // Divise la ligne en deux parties : le libellé et la valeur
                    if (parts.length == 2) {
                        contentStream.setFont(boldFont, fontSize); // Utiliser la police en gras pour les libellés
                        contentStream.showText(parts[0] + ": ");
                        contentStream.setFont(font, fontSize); // Revenir à la police normale pour les valeurs
                        contentStream.showText(parts[1]);
                    } else {
                        contentStream.showText(line);
                    }
                    yPosition -= leading; // Ajustement de l'espacement vertical
                    contentStream.newLineAtOffset(0, -leading); // Déplacement vers la ligne suivante
                    yPosition -= leading; // Décale la position verticale pour le contenu suivant
                }

                contentStream.endText(); // Fin du flux de texte pour chaque tâche
            }

            contentStream.close();

            boldInputStream.close(); // N'oubliez pas de fermer le flux boldInputStream


            document.save(filePath);
            document.close();

            System.out.println("PDF created successfully at: " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while creating the PDF: " + e.getMessage());
        }
    }

    private String replaceUnsupportedCharacters(String text) {
        // Replace the unsupported character � with a suitable replacement
        return text.replace("\uFFFD", "[Unsupported Character]");
    }
}
