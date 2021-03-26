package com.example.toys_inventory;

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

public class PdfExporterToy {
    private List<Toy> toyList;

    public PdfExporterToy(List<Toy> toyList) {
        this.toyList = toyList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
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

    private void writeTableData(PdfPTable table) {
        for (Toy newToy : toyList) {
            table.addCell(String.valueOf(newToy.getId()));
            table.addCell(newToy.getBrand());
            table.addCell(newToy.getName());
            table.addCell(String.valueOf(newToy.getQtyStart()));
            table.addCell(String.valueOf(newToy.getQtySold()));
            table.addCell(String.valueOf(newToy.getQtyStart() - newToy.getQtySold()));
            table.addCell(String.valueOf(newToy.getUnitPrice()));
            table.addCell(String.valueOf(newToy.getUnitPrice()*newToy.getQtySold()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(15);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Toys", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
