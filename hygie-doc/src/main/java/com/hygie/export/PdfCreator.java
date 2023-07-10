package com.hygie.export;

import com.hygie.hygietask.model.ResultTask;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.*;
import java.util.List;

public class PdfCreator {
    private final String title;
    private final String filePath;
    String fontPath = "C:\\WINDOWS\\FONTS\\times.ttf";
    File fontFile = new File(fontPath);
    InputStream inputStream = new FileInputStream(fontFile);

    public PdfCreator(String title, String filePath) throws FileNotFoundException {
        this.title = title;
        this.filePath = filePath;
    }

    public void createPdf(List<ResultTask> tasks) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDType0Font font = PDType0Font.load(document, inputStream);

            contentStream.setFont(font, 20);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText(title);
            contentStream.endText();

            contentStream.setFont(font, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 650);

            for (ResultTask task : tasks) {
                String taskString = replaceUnsupportedCharacters(task.toString());
                contentStream.showText(taskString);
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();

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
