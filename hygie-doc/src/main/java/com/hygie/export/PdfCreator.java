package com.hygie.export;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PdfCreator {
    private String title;
    private String filePath;

    public PdfCreator(String title, String filePath) {
        this.title = title;
        this.filePath = filePath;
    }

    public void createPdf() {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText(title);
            contentStream.endText();

            contentStream.close();

            document.save(filePath);
            document.close();

            System.out.println("PDF created successfully at: " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while creating the PDF: " + e.getMessage());
        }
    }


}
