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

            contentStream.setFont(font, fontSize); // Utiliser la police normale pour le texte principal

            int linesWritten = 0; // Nombre de lignes déjà écrites sur la page
            int maxLinesPerPage = 27; // Capacité maximale de lignes par page
            int currentPageNumber = 1; // Numéro de page initial

            // Ajouter le numéro de page en bas de page pour la première page
            contentStream.setFont(font, fontSize); // Utiliser la police normale pour le numéro de page
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, margin); // Position du numéro de page
            contentStream.showText("Page " + currentPageNumber); // Afficher le numéro de page
            contentStream.endText();

            for (ResultTask task : tasks) {
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

                int additionalLines = lines.length; // Nombre de lignes supplémentaires pour cette tâche

                if (linesWritten + additionalLines > maxLinesPerPage) {
                    currentPageNumber++; // Incrémenter le numéro de page
                    // Ajouter une nouvelle page
                    PDPage newPage = new PDPage(PDRectangle.A4);
                    document.addPage(newPage);
                    contentStream.close(); // Fermer le flux de contenu de la page précédente
                    contentStream = new PDPageContentStream(document, newPage); // Créer un nouveau flux de contenu pour la nouvelle page
                    yPosition = newPage.getMediaBox().getHeight() - margin - leading; // Réinitialiser la position verticale initiale
                    linesWritten = 0; // Réinitialiser le compteur de lignes

                    // Ajouter le numéro de page en bas de page
                    contentStream.setFont(font, fontSize); // Utiliser la police normale pour le numéro de page
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, margin); // Position du numéro de page
                    contentStream.showText("Page " + currentPageNumber); // Afficher le numéro de page
                    contentStream.endText();
                }

                contentStream.beginText(); // Début du flux de texte pour chaque tâche
                contentStream.setFont(font, fontSize); // Utiliser la police normale pour le texte principal
                contentStream.newLineAtOffset(margin, yPosition); // Position initiale du texte

                boolean isLabelDisplayed = false; // Indicateur si le libellé a déjà été affiché

                for (String line : lines) {
                    String[] parts = line.split(": ", 2); // Divise la ligne en deux parties : le libellé et la valeur

                    if (parts.length == 2) {
                        contentStream.setFont(boldFont, fontSize); // Utiliser la police en gras pour les libellés
                        contentStream.showText(parts[0] + ": ");
                        contentStream.setFont(font, fontSize); // Revenir à la police normale pour les valeurs

                        // Obtenir la largeur du libellé
                        float labelWidth = boldFont.getStringWidth(parts[0]) / 1000 * fontSize;

                        // Diviser la valeur en mots
                        String[] words = parts[1].split(" ");

                        float startX = margin + labelWidth + 5; // Position horizontale initiale pour la valeur
                        // Position verticale initiale pour la valeur
                        float currentPosition = startX;

                        for (String word : words) {
                            // Obtenir la largeur du mot
                            float wordWidth = font.getStringWidth(word) / 1000 * fontSize;

                            if (currentPosition + wordWidth > page.getMediaBox().getWidth() - margin) {
                                // Retour à la ligne si le mot dépasse la largeur de la page
                                contentStream.newLineAtOffset(0, -leading); // Déplacement vers la ligne suivante
                                contentStream.showText("      "); // Espacement pour aligner les valeurs avec le libellé
                                currentPosition = startX;
                            }

                            if (isLabelDisplayed) {
                                contentStream.setFont(font, fontSize); // Utiliser la police normale pour les valeurs
                            } else {
                                isLabelDisplayed = true;
                            }

                            contentStream.showText(word + " ");
                            currentPosition += wordWidth + fontSize;
                        }
                    } else {
                        contentStream.showText(line);
                    }

                    yPosition -= leading; // Ajustement de l'espacement vertical
                    contentStream.newLineAtOffset(0, -leading); // Déplacement vers la ligne suivante
                    yPosition -= leading; // Décale la position verticale pour le contenu suivant
                }

                contentStream.endText(); // Fin du flux de texte pour chaque tâche

                linesWritten += additionalLines; // Mettre à jour le nombre de lignes écrites
            }

            contentStream.close(); // Fermer le flux de contenu de la dernière page


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
        return text.replace("\uFFFD", "[?]");
    }
}