package com.example.toys_inventory;

import com.example.toys_inventory.DataModel.Game;
import com.example.toys_inventory.DataModel.Toy;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PdfExporterGame {
    private List<Game> gameList;

    public PdfExporterGame(List<Game> gameList) {
        this.gameList = gameList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("User ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Brand", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantity Start", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantity Sold", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantity on Hand", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Unit Price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total Sales", font));
        table.addCell(cell);
    }

    private void writeSecondTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Total quantity sold", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Total Sales", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (Game newGame : gameList) {
            table.addCell(String.valueOf(newGame.getId()));
            table.addCell(newGame.getBrand());
            table.addCell(newGame.getName());
            table.addCell(String.valueOf(newGame.getQtyStart()));
            table.addCell(String.valueOf(newGame.getQtySold()));
            table.addCell(String.valueOf(newGame.qtyOnHand()));
            table.addCell(String.valueOf(newGame.getUnitPrice()));
            table.addCell(String.valueOf(newGame.getUnitPrice()*newGame.getQtySold()));
        }
    }
    private void writeSecondTableData(PdfPTable table) {
        int itemsSold = 0;
        double totalSold = 0;
        for(Game newGame: gameList) {
            itemsSold += newGame.getQtySold();
            totalSold += newGame.totalSales();
        }
        table.addCell(String.valueOf(itemsSold));
        table.addCell(String.valueOf(totalSold));
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(15);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Games", font);
        Paragraph p2 = new Paragraph("Summary Information", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        p2.setAlignment(Paragraph.ALIGN_CENTER);


        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f});
        table.setSpacingBefore(10);

        PdfPTable table2 = new PdfPTable(2);
        table2.setWidthPercentage(100f);
        table2.setWidths(new float[] {1.25f,1.25f});
        table2.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);
        writeSecondTableHeader(table2);
        writeSecondTableData(table2);

        document.add(p);
        document.add(table);
        document.add(p2);
        document.add(table2);

        document.close();

    }
}
