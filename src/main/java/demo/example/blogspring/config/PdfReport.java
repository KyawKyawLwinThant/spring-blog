package demo.example.blogspring.config;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import demo.example.blogspring.model.Author;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
@Component
public class PdfReport {

  public static ByteArrayInputStream authorPdfViews(List<Author> authors){

    ByteArrayOutputStream out=new ByteArrayOutputStream();

    Document document=new Document();


    try {

      PdfPTable table = new PdfPTable(5);
      table.setWidthPercentage(80);
      table.setWidths(new int[]{1,3,3,3,3});


      PdfPCell hcell;
      Font font= FontFactory.getFont(FontFactory.HELVETICA);

      hcell=new PdfPCell(new Phrase("Id",font));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell=new PdfPCell(new Phrase("Name",font));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);


      hcell=new PdfPCell(new Phrase("Date Of Birth",font));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell=new PdfPCell(new Phrase("Gender",font));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell=new PdfPCell(new Phrase("Interested",font));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      for(Author author:authors){
        PdfPCell cell;

        cell=new PdfPCell(new Phrase(author.getId().toString()));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell=new PdfPCell(new Phrase(String.valueOf(author.getName())));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);


        cell=new PdfPCell(new Phrase(author.getDateOfBirth().toString()));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);


        cell=new PdfPCell(new Phrase(author.getGender().toString()));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);


        cell=new PdfPCell(new Phrase(String.valueOf(author.getInterested().toString())));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);


      }

      PdfWriter.getInstance(document,out);
      document.open();

      document.add(table);

      document.close();


    }catch (Exception e){
      e.printStackTrace();
    }

    return  new ByteArrayInputStream(out.toByteArray());
  }
}

