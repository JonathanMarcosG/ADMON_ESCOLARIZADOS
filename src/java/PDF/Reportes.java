/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDF;

import ConexionBD.IngresoAbd;
import DAO.ReportesDAO;
import Utils.Constants;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author David
 */
public class Reportes {

    ServletContext sc;
    Phrase[] header = new Phrase[2];
    int pagenumber;

    public Reportes(ServletContext sc) {
        this.sc = sc;
    }
    private com.itextpdf.text.Font fuenteNegra10 = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 9, com.itextpdf.text.Font.BOLD, BaseColor.BLACK);

    public class HeaderFooterPageEvent extends PdfPageEventHelper {

        String header;
        PdfTemplate total;

        public HeaderFooterPageEvent(String header) {
            this.header = header;
        }

        public void onOpenDocument(PdfWriter writer, Document document) {
            total = writer.getDirectContent().createTemplate(30, 16);
        }

        public void onStartPage(PdfWriter writer, Document document) {
            try {
                Rectangle rect = new Rectangle(30, 30, 550, 800);
                writer.setBoxSize("art", rect);

                String url_logo = "/Imagenes/" + header;
                String absolute_url_logo = sc.getRealPath(url_logo);
                Image itt_logo = Image.getInstance(absolute_url_logo);
                itt_logo.scaleAbsolute(290, 70);

                itt_logo.setAlignment(Element.ALIGN_CENTER);

                document.add(itt_logo);
                Paragraph vacio = new Paragraph("  ", FontFactory.getFont("arial", 10));
                vacio.setAlignment(Element.ALIGN_CENTER);
                document.add(vacio);
                document.add(vacio);

                pagenumber++;
            } catch (BadElementException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void onEndPage(PdfWriter writer, Document document) {

            PdfPTable table = new PdfPTable(2);
            try {
                table.setWidths(new int[]{10, 2});
                table.setTotalWidth(100);
                table.setLockedWidth(true);
                table.getDefaultCell().setFixedHeight(30);

                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                table.addCell(String.format("Página %d de", writer.getPageNumber()));
                Image totalP = Image.getInstance(total);
                totalP.scaleAbsoluteHeight(30);
                totalP.scaleAbsoluteWidth(40);
                PdfPCell cell = new PdfPCell(totalP);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);

                table.writeSelectedRows(0, -1, 34, 30, writer.getDirectContent());
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }

        public void onCloseDocument(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                    2, 2, 0);
        }
    }

    public class HeaderFooterPageEvent2 extends PdfPageEventHelper {

        String header;
        PdfTemplate total;

        public HeaderFooterPageEvent2(String header) {
            this.header = header;
        }

        public void onOpenDocument(PdfWriter writer, Document document) {
            total = writer.getDirectContent().createTemplate(30, 16);
        }

        public void onStartPage(PdfWriter writer, Document document) {
            try {
                Rectangle rect = new Rectangle(30, 30, 550, 800);
                writer.setBoxSize("art", rect);

                String url_logo = "/Imagenes/" + header;
                String absolute_url_logo = sc.getRealPath(url_logo);
                Image itt_logo = Image.getInstance(absolute_url_logo);
                itt_logo.scaleAbsolute(290, 70);

                itt_logo.setAlignment(Element.ALIGN_CENTER);

                document.add(itt_logo);
                Paragraph vacio = new Paragraph("  ", FontFactory.getFont("arial", 10));
                vacio.setAlignment(Element.ALIGN_CENTER);
                document.add(vacio);
                document.add(vacio);

            } catch (BadElementException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void onEndPage(PdfWriter writer, Document document) {

            PdfPTable table = new PdfPTable(2);
            PdfPTable leftText = new PdfPTable(1);
            try {
                table.setWidths(new int[]{10, 2});
                leftText.setWidths(new int[]{200});
                table.setTotalWidth(100);
                leftText.setTotalWidth(200);
                table.setLockedWidth(true);
                leftText.setLockedWidth(true);
                table.getDefaultCell().setFixedHeight(30);
                leftText.getDefaultCell().setFixedHeight(30);

                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                leftText.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                table.addCell(String.format("Página %d de", writer.getPageNumber()));
                leftText.addCell("ITTOL-ED-PO-001-01");
                Image totalP = Image.getInstance(total);
                totalP.scaleAbsoluteHeight(30);
                totalP.scaleAbsoluteWidth(40);
                PdfPCell cell = new PdfPCell(totalP);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);

                table.addCell(cell);

                leftText.writeSelectedRows(0, -1, 14, 30, writer.getDirectContent());
                table.writeSelectedRows(0, -1, 485, 30, writer.getDirectContent());
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }

        public void onCloseDocument(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                    2, 2, 0);
        }
    }

    public void reportesPDF(HttpServletResponse response, int tr, ServletContext d, String usuario, String contra, String horario, int opc) {
        String reporteT = "";
        try {

            Document reporte = new Document();
            Calendar cal = Calendar.getInstance();

            Paragraph intro = new Paragraph();
            intro.setAlignment(Element.ALIGN_CENTER);

            String linea = "/Imagenes/rallita.png";
            String absolute_url_linea = d.getRealPath(linea);

            Image linea_div = Image.getInstance(absolute_url_linea);

            Paragraph vacio = new Paragraph("  ", FontFactory.getFont("arial", 10));
            vacio.setAlignment(Element.ALIGN_CENTER);

            if (tr == 0) {
                PdfWriter writer = PdfWriter.getInstance(reporte, response.getOutputStream());
                Rectangle rect = new Rectangle(30, 30, 550, 800);
                writer.setBoxSize("art", rect);
                HeaderFooterPageEvent event = new HeaderFooterPageEvent("header_pdf.png");
                writer.setPageEvent(event);
                reporte.open();
                reporte.add(vacio);
                reporte.add(vacio);
                ArrayList<JFreeChart> graf = grafica(usuario, contra);
                if (graf == null) {

                    intro = new Paragraph("Lo sentimos, por el momento aún no existe información para este reporte.", FontFactory.getFont("arial", 18));

                    reporte.add(intro);
                } else {
                    for (int i = 0; i < graf.size(); i++) {
                        BufferedImage bufferedImage = graf.get(i).createBufferedImage(500, 300);
                        Image chart = Image.getInstance(writer, bufferedImage, 1.0f);
                        reporte.add(vacio);
                        reporte.add(chart);
                    }
                }
                reporteT = "Estadísticas de registros.";
            }
            if (tr == 1) {
                PdfWriter writer = PdfWriter.getInstance(reporte, response.getOutputStream());
                Rectangle rect = new Rectangle(30, 30, 550, 800);
                writer.setBoxSize("art", rect);
                HeaderFooterPageEvent event = new HeaderFooterPageEvent("header_pdf.png");
                writer.setPageEvent(event);
                reporte.open();
                reporte.add(linea_div);
                Paragraph creador = new Paragraph("Instituto Tecnológico de Toluca\n"
                        + "\n"
                        + "Centro de Cómputo\n"
                        + "\n"
                        + "Coordinación de Desarrollo de Sistemas", FontFactory.getFont("arial", 10));
                reporte.add(creador);
                reporte.add(linea_div);
                intro = new Paragraph("Sin alta en CENEVAL " + cal.get(Calendar.YEAR), FontFactory.getFont("arial", 18));
                intro.setAlignment(Element.ALIGN_CENTER);
                reporte.add(intro);
                reporte.add(vacio);
                reporte.add(vacio);
                reporte.add(noaltaCen(usuario, contra));
                reporteT = "Sin alta en CENEVAL.";
            }
            if (tr == 2) {
                PdfWriter writer = PdfWriter.getInstance(reporte, response.getOutputStream());
                Rectangle rect = new Rectangle(30, 30, 550, 800);
                writer.setBoxSize("art", rect);
                HeaderFooterPageEvent event = new HeaderFooterPageEvent("header_pdf.png");
                writer.setPageEvent(event);
                reporte.open();
                reporte.add(linea_div);
                Paragraph creador = new Paragraph("Instituto Tecnológico de Toluca\n"
                        + "\n"
                        + "Centro de Cómputo\n"
                        + "\n"
                        + "Coordinación de Desarrollo de Sistemas", FontFactory.getFont("arial", 10));
                reporte.add(creador);
                reporte.add(linea_div);
                intro = new Paragraph("Estatus Prefichas " + cal.get(Calendar.YEAR), FontFactory.getFont("arial", 18));
                intro.setAlignment(Element.ALIGN_CENTER);
                reporte.add(intro);
                reporte.add(vacio);
                reporte.add(vacio);

                reporte.add(statusfichas(usuario, contra));
                reporteT = "Estatus prefichas";
            }
            if (tr == 3) {
                PdfWriter writer = PdfWriter.getInstance(reporte, response.getOutputStream());
                Rectangle rect = new Rectangle(30, 30, 550, 800);
                writer.setBoxSize("art", rect);
                HeaderFooterPageEvent event = new HeaderFooterPageEvent("header_pdf.png");
                writer.setPageEvent(event);
                reporte.open();
                reporte.add(linea_div);
                Paragraph creador = new Paragraph("Instituto Tecnológico de Toluca\n"
                        + "\n"
                        + "Centro de Cómputo\n"
                        + "\n"
                        + "Coordinación de Desarrollo de Sistemas", FontFactory.getFont("arial", 10));
                reporte.add(creador);
                reporte.add(linea_div);
                intro = new Paragraph("Pre proceso concluido " + cal.get(Calendar.YEAR), FontFactory.getFont("arial", 18));
                intro.setAlignment(Element.ALIGN_CENTER);
                reporte.add(intro);
                reporte.add(vacio);
                reporte.add(vacio);
                reporte.add(procesoCon(usuario, contra));
                reporteT = "Pre proceso concluido";
            }
            if (tr == 4) {

                PdfWriter writer = PdfWriter.getInstance(reporte, response.getOutputStream());
                ArrayList<PdfPTable> tables = firmasAspAula(usuario, contra, horario, opc);
                Rectangle rect = new Rectangle(30, 30, 550, 800);
                writer.setBoxSize("art", rect);
                HeaderFooterPageEvent2 event = new HeaderFooterPageEvent2("ficha-pdf.png");
                writer.setPageEvent(event);
                reporte.open();
                
                if (tables.size() != 1) {
                    PdfPTable tableH = tables.get(tables.size() - 1);
                    tableH.writeSelectedRows(0, -1, 10, 720, writer.getDirectContent());
                } else {
                    reporte.add(tables.get(0));
                }
                reporte.add(vacio);

                reporte.add(vacio);
                reporte.add(vacio);
                for (int i = 0; i < tables.size(); i++) {

                    if (i + 1 != tables.size()) {

                        reporte.add(tables.get(i));
                        if (i + 2 != tables.size()) {
                            reporte.newPage();
                        }
                    }

                }

                reporteT = "Firmas Aspirantes_" + horario;

            }
            if (tr == 5) {

                PdfWriter writer = PdfWriter.getInstance(reporte, response.getOutputStream());
                ArrayList<PdfPTable> tables = tablaAspAula(usuario, contra, horario, opc);
                Rectangle rect = new Rectangle(30, 30, 550, 800);
                writer.setBoxSize("art", rect);
                HeaderFooterPageEvent2 event = new HeaderFooterPageEvent2("ficha-pdf.png");
                writer.setPageEvent(event);
                reporte.open();
                if (tables.size() != 1) {
                    PdfPTable tableH = tables.get(tables.size() - 1);
                    tableH.writeSelectedRows(0, -1, 10, 720, writer.getDirectContent());
                } else {
                    reporte.add(tables.get(0));
                }

                reporte.add(vacio);

                reporte.add(vacio);
                reporte.add(vacio);
                for (int i = 0; i < tables.size(); i++) {

                    if (i + 1 != tables.size()) {

                        reporte.add(tables.get(i));
                        if (i + 2 != tables.size()) {
                            reporte.newPage();
                        }
                    }

                }

                reporteT = "Aspirantes por aula horario_" + horario;

            }

            reporte.addTitle("Reportes_" + reporteT);
            reporte.addSubject("Instituto Tecnológico de Toluca");
            reporte.addKeywords("Instituto Tecnológico de Toluca");
            reporte.addAuthor("Coordinacion de desarrollo de sistemas");
            reporte.addCreator("Centro de Cómputo ITT");

            //Asignamos el manejador de eventos al escritor.
            reporte.close();

        } catch (DocumentException | IOException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<PdfPTable> tablaAspAula(String usuario, String contra, String horario, int opc) throws DocumentException {
        ArrayList<PdfPTable> tablas = new ArrayList();
//        IngresoAbd bd = new IngresoAbd(usuario, contra);
        List<Beans.Reportes> reportes = ReportesDAO.AspPAula(usuario, contra, horario, opc);
        PdfPTable table = new PdfPTable(2);
//        reportes = bd.AspPAula(horario, opc);
        PdfPCell cell;
        String carrera = "", fecha = "";
        if (reportes.isEmpty()) {

            cell = new PdfPCell(new Phrase("Lo sentimos, por el momento aún no existe información para este reporte."));
            cell.setColspan(5);
            table.addCell(cell);
            tablas.add(table);

        } else if (reportes.get(0).getCodError() != 0) {

            if (reportes.get(0).getCodError() == -1) {
                cell = new PdfPCell(new Phrase(Constants.ERROR1));
                cell.setColspan(5);
                table.addCell(cell);
            }
            if (reportes.get(0).getCodError() == -2) {
                cell = new PdfPCell(new Phrase(Constants.ERROR3));
                cell.setColspan(5);
                table.addCell(cell);
            }
            if (reportes.get(0).getCodError() == -3) {
                cell = new PdfPCell(new Phrase(Constants.ERROR2));
                cell.setColspan(5);
                table.addCell(cell);
            }
            tablas.add(table);
        } else {
            table = new PdfPTable(5);
            BaseColor color = new BaseColor(217, 217, 217);
            BaseColor colorB = new BaseColor(0, 0, 0);
            cell = new PdfPCell(new Phrase("Ficha(5)", FontFactory.getFont("arial", 12, colorB)));
            cell.setMinimumHeight(10);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);

            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Folio Ceneval(6)", FontFactory.getFont("arial", 12, colorB)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nombre(7)", FontFactory.getFont("arial", 12, colorB)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            table.addCell(cell);

//            cell = new PdfPCell(new Phrase("Nombre carrera", FontFactory.getFont("arial", 12, colorB)));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//             cell.setBackgroundColor(color);
//             cell.setBorderColor(colorB);
//            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Asistencia(8)", FontFactory.getFont("arial", 12, colorB)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            cell.setColspan(2);
            table.addCell(cell);
//            cell = new PdfPCell(new Phrase("Firma", FontFactory.getFont("arial", 12, colorB)));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//             cell.setBackgroundColor(color);
//             cell.setBorderColor(colorB);
//            table.addCell(cell);

            for (int i = 0; i < reportes.size(); i++) {

                cell = new PdfPCell(new Phrase(reportes.get(i).getFicha()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reportes.get(i).getFolio()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reportes.get(i).getNombre(), FontFactory.getFont("arial", 10)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                carrera = reportes.get(i).getNom_carrera();
//               
                cell = new PdfPCell(new Phrase(reportes.get(i).getAsist()));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reportes.get(i).getFirma()));
                table.addCell(cell);
                fecha = reportes.get(i).getFecha();
                if ((i % 34 == 0 || (i + 1) == reportes.size()) && i != 0) {

                    table.setWidthPercentage(110);
                    table.setWidths(new int[]{25, 30, 120, 15, 15});
                    tablas.add(table);
                    table = new PdfPTable(5);
                }
            }
            String datos[] = horario.split(" ");
            String edificio = datos[0];
            String aula = "";
            if (edificio.length() == 2) {
                edificio = datos[0].charAt(0) + "";
                aula = datos[0].charAt(1) + "";
            }
            if (edificio.length() == 4) {

                edificio = datos[0].charAt(0) + "" + datos[0].charAt(1);
                aula = "" + datos[0].charAt(3);
            }
            if (edificio.length() == 5) {

                edificio = datos[0].charAt(0) + "" + datos[0].charAt(1);
                aula = "" + datos[0].charAt(3) + datos[0].charAt(4);
            }
            PdfPTable tableH = new PdfPTable(7);
            tableH.setTotalWidth(500);
            cell.setMinimumHeight(20);
            cell = new PdfPCell(new Phrase("CARRERA:(1)", FontFactory.getFont("arial", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase(carrera));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase("FECHA DE EXAMEN:(2)", FontFactory.getFont("arial", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase(fecha));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase("EDIFICIO:(3)", FontFactory.getFont("arial", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase(edificio));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase("AULA:(4)", FontFactory.getFont("arial", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase(aula));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableH.addCell(cell);

            tablas.add(tableH);
        }

        return tablas;
    }

    public static ArrayList<PdfPTable> firmasAspAula(String usuario, String contra, String horario, int opc) throws DocumentException {
        ArrayList<PdfPTable> tablas = new ArrayList();
//        IngresoAbd bd = new IngresoAbd(usuario, contra);
        List<Beans.Reportes> reportes;
        PdfPTable table = new PdfPTable(2);
        reportes = ReportesDAO.AspPAula(usuario, contra, horario, opc);
//        reportes = bd.AspPAula(horario, opc);
        String carrera = "", fecha = "";
        if (reportes.isEmpty()) {
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Lo sentimos, por el momento aún no existe información para este reporte."));
            cell.setColspan(5);
            table.addCell(cell);
            tablas.add(table);
        } else if (reportes.get(0).getCodError() != 0) {
            PdfPCell cell;
            if (reportes.get(0).getCodError() == -1) {
                cell = new PdfPCell(new Phrase(Constants.ERROR1));
                cell.setColspan(5);
                table.addCell(cell);
            }
            if (reportes.get(0).getCodError() == -2) {
                cell = new PdfPCell(new Phrase(Constants.ERROR3));
                cell.setColspan(5);
                table.addCell(cell);
            }
            if (reportes.get(0).getCodError() == -3) {
                cell = new PdfPCell(new Phrase(Constants.ERROR2));
                cell.setColspan(5);
                table.addCell(cell);
            }
            tablas.add(table);
        } else {
            PdfPCell cell;
            table = new PdfPTable(6);
            BaseColor color = new BaseColor(217, 217, 217);
            BaseColor colorB = new BaseColor(0, 0, 0);
            cell = new PdfPCell(new Phrase("Ficha(5)", FontFactory.getFont("arial", 12, colorB)));
            cell.setMinimumHeight(10);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);

            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Folio Ceneval(6)", FontFactory.getFont("arial", 12, colorB)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nombre(7)", FontFactory.getFont("arial", 12, colorB)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            table.addCell(cell);

//            cell = new PdfPCell(new Phrase("Nombre carrera", FontFactory.getFont("arial", 12, colorB)));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//             cell.setBackgroundColor(color);
//             cell.setBorderColor(colorB);
//            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Asistencia(8)", FontFactory.getFont("arial", 12, colorB)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            cell.setColspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Firma(9)", FontFactory.getFont("arial", 12, colorB)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            table.addCell(cell);

            for (int i = 0; i < reportes.size(); i++) {
                cell = new PdfPCell(new Phrase(reportes.get(i).getFicha()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reportes.get(i).getFolio()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reportes.get(i).getNombre(), FontFactory.getFont("arial", 10)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                carrera = reportes.get(i).getNom_carrera();
//               
                cell = new PdfPCell(new Phrase(reportes.get(i).getAsist()));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reportes.get(i).getFirma()));
                table.addCell(cell);

                table.addCell(cell);
                fecha = reportes.get(i).getFecha();

                if ((i % 34 == 0 || (i + 1) == reportes.size()) && i != 0) {

                    table.setWidthPercentage(110);
                    table.setWidths(new int[]{25, 30, 90, 15, 15, 30});
                    tablas.add(table);
                    table = new PdfPTable(6);
                }
            }
            String datos[] = horario.split(" ");
            String edificio = datos[0];
            String aula = "";
            if (edificio.length() == 2) {
                edificio = datos[0].charAt(0) + "";
                aula = datos[0].charAt(1) + "";
            }
            if (edificio.length() == 4) {

                edificio = datos[0].charAt(0) + "" + datos[0].charAt(1);
                aula = "" + datos[0].charAt(3);
            }
            if (edificio.length() == 5) {

                edificio = datos[0].charAt(0) + "" + datos[0].charAt(1);
                aula = "" + datos[0].charAt(3) + datos[0].charAt(4);
            }
            PdfPTable tableH = new PdfPTable(7);
            tableH.setTotalWidth(500);
            cell.setMinimumHeight(20);
            cell = new PdfPCell(new Phrase("CARRERA:(1)", FontFactory.getFont("arial", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase(carrera));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase("FECHA DE EXAMEN:(2)", FontFactory.getFont("arial", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase(fecha));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase("EDIFICIO:(3)", FontFactory.getFont("arial", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase(edificio));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase("AULA:(4)", FontFactory.getFont("arial", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableH.addCell(cell);
            cell = new PdfPCell(new Phrase(aula));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableH.addCell(cell);

            tablas.add(tableH);

        }

        return tablas;
    }

    public static PdfPTable noaltaCen(String usuario, String contra) throws DocumentException {
        PdfPTable table = new PdfPTable(5);
//        IngresoAbd bd = new IngresoAbd(usuario, contra);
        List<Beans.Reportes> reportes;
        reportes = ReportesDAO.noAltaCen(usuario, contra);
//        reportes = bd.noAltaCen();
        if (reportes.isEmpty()) {
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Lo sentimos, por el momento aún no existe información para este reporte."));
            cell.setColspan(5);
            table.addCell(cell);
        } else if (reportes.get(0).getCodError() != 0) {
            PdfPCell cell;
            if (reportes.get(0).getCodError() == -1) {
                cell = new PdfPCell(new Phrase(Constants.ERROR1));
                cell.setColspan(5);
                table.addCell(cell);
            }
            if (reportes.get(0).getCodError() == -2) {
                cell = new PdfPCell(new Phrase(Constants.ERROR3));
                cell.setColspan(5);
                table.addCell(cell);
            }
            if (reportes.get(0).getCodError() == -3) {
                cell = new PdfPCell(new Phrase(Constants.ERROR2));
                cell.setColspan(5);
                table.addCell(cell);
            }
//            cell = new PdfPCell(new Phrase(reportes.get(0).getMsjError()));
        } else {
            BaseColor color = new BaseColor(69, 161, 240);
            BaseColor colorB = new BaseColor(255, 255, 255);
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Preficha", FontFactory.getFont("arial", 12, colorB)));
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Ref. Bancaria", FontFactory.getFont("arial", 12, colorB)));
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Correo", FontFactory.getFont("arial", 12, colorB)));
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Usuario", FontFactory.getFont("arial", 12, colorB)));
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Última Act.", FontFactory.getFont("arial", 12, colorB)));
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            for (Beans.Reportes reporte : reportes) {
                cell = new PdfPCell(new Phrase(reporte.getPreficha()));
                cell.setBorderColor(color);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reporte.getReferencia()));
                cell.setBorderColor(color);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reporte.getCorreo()));
                cell.setBorderColor(color);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reporte.getUsuario()));
                cell.setBorderColor(color);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reporte.getUl_act().substring(0, 10)));
                cell.setBorderColor(color);
                table.addCell(cell);

            }

            table.setWidthPercentage(110);
            table.setWidths(new int[]{50, 55, 100, 56, 56});
        }

        return table;
    }

    public static PdfPTable procesoCon(String usuario, String contra) throws DocumentException {

//        IngresoAbd bd = new IngresoAbd(usuario, contra);
        List<Beans.Reportes> reportes;
        reportes = ReportesDAO.procesoCon(usuario, contra);
//        reportes = bd.procesoCon();
        PdfPTable table = new PdfPTable(2);

        if (reportes.isEmpty()) {
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Lo sentimos, por el momento aún no existe información para este reporte."));
            cell.setColspan(5);
            table.addCell(cell);
        } else if (reportes.get(0).getCodError() != 0) {
            PdfPCell cell;
            if (reportes.get(0).getCodError() == -1) {
                cell = new PdfPCell(new Phrase(Constants.ERROR1));
                cell.setColspan(5);
                table.addCell(cell);
            }
            if (reportes.get(0).getCodError() == -2) {
                cell = new PdfPCell(new Phrase(Constants.ERROR3));
                cell.setColspan(5);
                table.addCell(cell);
            }
            if (reportes.get(0).getCodError() == -3) {
                cell = new PdfPCell(new Phrase(Constants.ERROR2));
                cell.setColspan(5);
                table.addCell(cell);
            }
//            cell = new PdfPCell(new Phrase(reportes.get(0).getMsjError()));
        } else {
            BaseColor color = new BaseColor(69, 161, 240);
            BaseColor colorB = new BaseColor(255, 255, 255);
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Carrera", FontFactory.getFont("arial", 12, colorB)));

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(colorB);
            cell.setBackgroundColor(color);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("No. de pre procesos concluidos", FontFactory.getFont("arial", 12, colorB)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(colorB);

            cell.setBackgroundColor(color);
            table.addCell(cell);
            int total = 0;
            for (Beans.Reportes reporte : reportes) {

                cell = new PdfPCell(new Phrase(reporte.getNombre(), FontFactory.getFont("arial", 8)));

                cell.setBorderColor(color);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reporte.getPreproc()));

                cell.setBorderColor(color);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                total = total + Integer.parseInt(reporte.getPreproc());
                table.addCell(cell);
            }

            cell = new PdfPCell(new Phrase("Total", FontFactory.getFont("arial", 12, colorB)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(colorB);
            cell.setBackgroundColor(color);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(Integer.toString(total), FontFactory.getFont("arial", 12)));
            cell.setBorderColor(color);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            table.setWidthPercentage(110);

        }

        return table;
    }

    public static PdfPTable statusfichas(String usuario, String contra) throws DocumentException {

        IngresoAbd bd = new IngresoAbd(usuario, contra);
        List<Beans.Reportes> reportes;
        reportes = ReportesDAO.statusFichas(usuario, contra);
//        reportes = bd.statusFichas();

        PdfPTable table = new PdfPTable(4);
        if (reportes.isEmpty()) {

            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Lo sentimos, por el momento aún no existe información para este reporte."));
            cell.setColspan(5);
            table.addCell(cell);
        } else if (reportes.get(0).getCodError() != 0) {
            PdfPCell cell;
            if (reportes.get(0).getCodError() == -1) {
                cell = new PdfPCell(new Phrase(Constants.ERROR1));
                cell.setColspan(5);
                table.addCell(cell);
            }
            if (reportes.get(0).getCodError() == -2) {
                cell = new PdfPCell(new Phrase(Constants.ERROR3));
                cell.setColspan(5);
                table.addCell(cell);
            }
            if (reportes.get(0).getCodError() == -3) {
                cell = new PdfPCell(new Phrase(Constants.ERROR2));
                cell.setColspan(5);
                table.addCell(cell);
            }
//            cell = new PdfPCell(new Phrase(reportes.get(0).getMsjError()));
        } else {
            BaseColor color = new BaseColor(69, 161, 240);
            BaseColor colorB = new BaseColor(255, 255, 255);
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Carrera", FontFactory.getFont("arial", 12, colorB)));
            cell.setBorderColor(colorB);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Prefichas", FontFactory.getFont("arial", 12, colorB)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);

            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Prefichas pagadas", FontFactory.getFont("arial", 12, colorB)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Pre proceso concluido", FontFactory.getFont("arial", 12, colorB)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            table.addCell(cell);
            int totalP = 0;
            int totalPp = 0;
            int totalPr = 0;
            for (Beans.Reportes reporte : reportes) {
                cell = new PdfPCell(new Phrase(reporte.getNombre(), FontFactory.getFont("arial", 8)));
                cell.setBorderColor(color);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reporte.getPreficha()));
                cell.setBorderColor(color);
                totalP = Integer.parseInt(reporte.getPreficha()) + totalP;
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reporte.getPrefpagadas()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorderColor(color);
                totalPp = Integer.parseInt(reporte.getPrefpagadas()) + totalPp;
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(reporte.getPreproc()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorderColor(color);
                totalPr = Integer.parseInt(reporte.getPreproc()) + totalPr;
                table.addCell(cell);

            }
            cell = new PdfPCell(new Phrase("Totales", FontFactory.getFont("arial", 12, colorB)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(color);
            cell.setBorderColor(colorB);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(Integer.toString(totalP), FontFactory.getFont("arial", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(color);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(Integer.toString(totalPp), FontFactory.getFont("arial", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(color);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(Integer.toString(totalPr), FontFactory.getFont("arial", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(color);
            table.addCell(cell);
            table.setWidthPercentage(110);
            table.setWidths(new int[]{50, 50, 100, 100});
        }

        return table;
    }

    public ArrayList<JFreeChart> grafica(String usuario, String contra) {
//        IngresoAbd bd = new IngresoAbd(usuario, contra);
        ArrayList<JFreeChart> graficas = new ArrayList();
        List<Beans.Reportes> reportes = ReportesDAO.statusFichas(usuario, contra);
//        reportes = bd.statusFichas();
        Beans.Reportes report = ReportesDAO.preFichas(usuario, contra);
//        report = bd.preFichas();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        DefaultPieDataset dataset3 = new DefaultPieDataset();
        if (reportes == null || report == null) {
            return graficas;
        } else {
            for (Beans.Reportes reporte : reportes) {
                String carrera = "";

                if (reporte.getNombre().contentEquals("INGENIERÍA MECATRÓNICA")) {
                    carrera = "IMCT";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA ELECTROMECÁNICA")) {
                    carrera = "IEME";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA EN SISTEMAS COMPUTACIONALES")) {
                    carrera = "ISC";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA ELECTRÓNICA")) {
                    carrera = "IELC";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA EN LOGÍSTICA")) {
                    carrera = "ILOG";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA INDUSTRIAL")) {
                    carrera = "IIND";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA EN GESTION EMPRESARIAL")) {
                    carrera = "IGEM";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA EN TECNOLOGÍAS DE LA INFORMACIÓN Y COMUNICACIONES")) {
                    carrera = "TICS";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA QUIMICA")) {
                    carrera = "IQUI";
                }

                dataset.setValue(Integer.parseInt(reporte.getPreficha()), reporte.getPreficha(), carrera);
            }
            // Visitas del sitio web 2

            JFreeChart chart = ChartFactory.createBarChart3D("Prefichas por carrera", "",
                    "Número de prefichas", dataset, PlotOrientation.HORIZONTAL, true,
                    true, false);

            for (Beans.Reportes reporte : reportes) {
                String carrera = "";

                if (reporte.getNombre().contentEquals("INGENIERÍA MECATRÓNICA")) {
                    carrera = "IMCT";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA ELECTROMECÁNICA")) {
                    carrera = "IEME";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA EN SISTEMAS COMPUTACIONALES")) {
                    carrera = "ISC";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA ELECTRÓNICA")) {
                    carrera = "IELC";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA EN LOGÍSTICA")) {
                    carrera = "ILOG";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA INDUSTRIAL")) {
                    carrera = "IIND";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA EN GESTION EMPRESARIAL")) {
                    carrera = "IGEM";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA EN TECNOLOGÍAS DE LA INFORMACIÓN Y COMUNICACIONES")) {
                    carrera = "TICS";
                }
                if (reporte.getNombre().contentEquals("INGENIERÍA QUIMICA")) {
                    carrera = "IQUI";
                }

                dataset2.setValue(Integer.parseInt(reporte.getPrefpagadas()), reporte.getPrefpagadas(), carrera);
            }

            JFreeChart chart2 = ChartFactory.createBarChart3D("Prefichas pagadas por carrera", "",
                    "Número de prefichas", dataset2, PlotOrientation.HORIZONTAL, true,
                    true, false);
            int prefichas = 0;
            if (Integer.parseInt(report.getPreficha()) >= 0) {
                prefichas = Integer.parseInt(report.getPreficha());
            }

            dataset3.setValue("Prefichas pagadas: " + report.getPrefpagadas(), Integer.parseInt(report.getPrefpagadas()));
            dataset3.setValue("Prefichas por pagar: " + report.getPreproc(), Integer.parseInt(report.getPreproc()));
            dataset3.setValue("Prefichas disponibles: " + prefichas, prefichas);

            JFreeChart chart3 = ChartFactory.createPieChart3D("Meta Deseada: " + report.getNombre(), dataset3);
            graficas.add(chart3);
            graficas.add(chart);
            graficas.add(chart2);
        }
        return graficas;
    }
}
