package PDF;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import Beans.DatosPDF;
import Beans.DatosPDF;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;

/**
 *
 * @author Rocio
 */
public class CrearPDF_Ficha {

    public void generarPDF(ServletOutputStream sops, DatosPDF datos, String url) {

        try {

            Document documento = new Document();
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(documento, sops);
            documento.open();

            Image itt_logo;
            try {
                itt_logo = Image.getInstance(url);
                Image Logo_itt = Image.getInstance(itt_logo);
                Logo_itt.setAbsolutePosition(50f, 698f);
                Logo_itt.scaleAbsolute(90, 100);
                documento.add(Logo_itt);
            } catch (BadElementException | IOException ex) {
                Logger.getLogger(CrearPDF_Ficha.class.getName()).log(Level.SEVERE, null, ex);
            }

            PdfContentByte rectangulo_info = writer.getDirectContentUnder();
            drawRectangle(rectangulo_info, 430, 648, 90, 100);
            Paragraph leyendaFoto = new Paragraph("\nFOTO:\n", FontFactory.getFont("arial", 14, Font.BOLD));
            leyendaFoto.setIndentationLeft(200f);
            Paragraph titulo = new Paragraph("INSTITUTO TECNOLÓGICO DE TOLUCA", FontFactory.getFont("arial", 14));
            titulo.setAlignment(Element.ALIGN_CENTER);

            Paragraph asunto = new Paragraph("FICHA DE EXAMEN", FontFactory.getFont("arial", 12));
            asunto.setAlignment(Element.ALIGN_CENTER);

            Chunk folio1 = new Chunk("FICHA PARA EL EXAMEN DE ADMISIÓN: ", FontFactory.getFont("arial", 10));
            Chunk folio2 = new Chunk(datos.getFicha(), FontFactory.getFont("arial", 10, Font.BOLD));

            Phrase fol = new Phrase();
            fol.add(folio1);
            fol.add(folio2);

            Paragraph noFicha = new Paragraph(fol);
            noFicha.setAlignment(Element.ALIGN_LEFT);

            Chunk nombre1 = new Chunk("NOMBRE DEL SOLICITANTE: ", FontFactory.getFont("arial", 10));
            Chunk nombre2 = new Chunk(datos.getNombre(), FontFactory.getFont("arial", 10, Font.BOLD));

            Phrase nom = new Phrase();
            nom.add(nombre1);
            nom.add(nombre2);

            Paragraph nombre = new Paragraph(nom);
            nombre.setAlignment(Element.ALIGN_LEFT);

            Chunk in1 = new Chunk("PROCESO PARA EL REGISTRO DE ASPIRANTES EN EL PERIODO: ", FontFactory.getFont("arial", 10));
            Chunk in2 = new Chunk(datos.getPeriodoConcursa().toUpperCase(), FontFactory.getFont("arial", 10, Font.BOLD));

            Phrase in = new Phrase();
            in.add(in1);
            in.add(in2);

            Paragraph instrucciones = new Paragraph(in);
            instrucciones.setAlignment(Element.ALIGN_LEFT);

            Chunk folCen1 = new Chunk("1.- NÚMERO DE FOLIO CENEVAL: ", FontFactory.getFont("arial", 10));
            Chunk folCen2 = new Chunk(datos.getFolioCENEVAL(), FontFactory.getFont("arial", 10, Font.BOLD));

            Phrase folC = new Phrase();
            folC.add(folCen1);
            folC.add(folCen2);

            Paragraph folioCENEVAL = new Paragraph(folC);
            folioCENEVAL.setAlignment(Element.ALIGN_LEFT);

            Chunk fechas1 = new Chunk("2.- LOS EXÁMENES DE ADMISIÓN SE APLICARÁN LOS DÍAS: ", FontFactory.getFont("arial", 10));
            Chunk fechas2 = new Chunk(datos.getFechaExamenCeneval()+" ("+datos.getLugarExamenCeneval()+")", FontFactory.getFont("arial", 10, Font.BOLD));
            Chunk fechas3 = new Chunk(" Y ", FontFactory.getFont("arial", 10));
            Chunk fechas4 = new Chunk(datos.getFechaExamenMate()+" ("+datos.getLugarExamenMate()+")", FontFactory.getFont("arial", 10, Font.BOLD));

            Phrase fechas = new Phrase();
            fechas.add(fechas1);
            fechas.add(fechas2);
            fechas.add(fechas3);
            fechas.add(fechas4);

            Paragraph fechaExamenes = new Paragraph(fechas);
            fechaExamenes.setAlignment(Element.ALIGN_LEFT);

           

            Phrase lugar = new Phrase();
         

            Paragraph lugarYhora = new Paragraph(lugar);
            lugarYhora.setAlignment(Element.ALIGN_LEFT);

            Chunk paginaPub1 = new Chunk("3.- LA PUBLICACIÓN DE LOS RESULTADOS SERÁ ÚNICAMENTE EN LA PÁGINA WEB: ", FontFactory.getFont("arial", 10));

            Anchor url_itt = new Anchor(datos.getPagResultados());
            url_itt.setReference(datos.getPagResultados());

            Phrase pag = new Phrase();
            pag.add(paginaPub1);
            pag.add(url_itt);

            Paragraph pagWeb = new Paragraph(pag);

            Chunk diaPub1 = new Chunk("EL DÍA: ", FontFactory.getFont("arial", 10));
            Chunk diaPub2 = new Chunk(convertir(datos.getDiaPublicacion() + "-"), FontFactory.getFont("arial", 10, Font.BOLD));

            Phrase dia = new Phrase();
            dia.add(diaPub1);
            dia.add(diaPub2);

            Paragraph diaResultados = new Paragraph(dia);
            diaResultados.setAlignment(Element.ALIGN_LEFT);

            Chunk notas = new Chunk("\nNOTAS:\n", FontFactory.getFont("arial", 14, Font.BOLD));

            Chunk uno = new Chunk("1.- ", FontFactory.getFont("arial", 10, Font.BOLD));

            Chunk guias = new Chunk("Guías de estudio:\n   - (CENEVAL) \n", FontFactory.getFont("arial", 10));

            Anchor url_guia_cen = new Anchor("     " + datos.getEstudioCeneval());
            // url_guia_cen.setReference(datos.getEstudioCeneval());

            Chunk ceneval_inter = new Chunk("\n   - (CENEVAL INTERACTIVA) \n", FontFactory.getFont("arial", 10));

            Anchor url_guia_cen_inter = new Anchor("     " + datos.getEstudioCenevalInt());
            url_guia_cen_inter.setReference(datos.getEstudioCenevalInt());

            Chunk tem_mate_itt = new Chunk("\n   - Temario de Matemáticas (TECNOLÓGICO DE TOLUCA)\n\n", FontFactory.getFont("arial", 10));
            Chunk dos = new Chunk("2.- ", FontFactory.getFont("arial", 10, Font.BOLD));
            Chunk veri = new Chunk("Verifique que el número de folio de Ceneval de esta ficha, coincida con el", FontFactory.getFont("arial", 10));
            Chunk fol_ceneval = new Chunk(" FOLIO CENEVAL ", FontFactory.getFont("arial", 10, Font.BOLD));
            Chunk capturado = new Chunk("capturado en la información proporcionada por el Tecnólogico.\n\n", FontFactory.getFont("arial", 10));
            Chunk tres = new Chunk("3.- ", FontFactory.getFont("arial", 10, Font.BOLD));
            Chunk dia_exam = new Chunk("El día del examen deberá presentarse con el presente documento, pase de ingreso al examen(Ceneval), una identificación con fotografía reciente(credencial escolar, IMSS, ISSSTE, ISSEMYM, licencia, pasaporte), lápiz del número 2 y goma.\n\n", FontFactory.getFont("arial", 10));
            Chunk cuatro = new Chunk("4.- ", FontFactory.getFont("arial", 10, Font.BOLD));
            Chunk curso = new Chunk("Si cursó sus estudios de secundaria o bachillerato en el extranjero deberá presentar revalidación de estudios correspondientes al momento de la inscripción.\n", FontFactory.getFont("arial", 10));
            Chunk cinco = new Chunk("\n5.- ", FontFactory.getFont("arial", 10, Font.BOLD));
            Chunk examenes = new Chunk("Los exámenes que se evaluarán son:       1) ADMISIÓN Y DIAGNÓSTICO.     2) MATEMÁTICAS.", FontFactory.getFont("arial", 10));

            Phrase ulti = new Phrase();
            ulti.add(notas);
            ulti.add(uno);
            ulti.add(guias);
            ulti.add(url_guia_cen);
            ulti.add(ceneval_inter);
            ulti.add(url_guia_cen_inter);
            ulti.add(tem_mate_itt);
            ulti.add(dos);
            ulti.add(veri);
            ulti.add(fol_ceneval);
            ulti.add(capturado);
            ulti.add(tres);
            ulti.add(dia_exam);
            ulti.add(cuatro);
            ulti.add(curso);
            ulti.add(cinco);
            ulti.add(examenes);

            Paragraph ultimo = new Paragraph(ulti);
            ultimo.setAlignment(Element.ALIGN_LEFT);

            documento.addTitle("Ficha de Examen");
            documento.addSubject("Instituto Tecnológico de Toluca");
            documento.addKeywords("Instituto Tecnológico de Toluca");
            documento.addAuthor("Departamento de Servicios escolares");
            documento.addCreator("Departamento de Servicios escolares");
            documento.add(titulo);
            documento.add(asunto);
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));

            documento.add(noFicha);
            documento.add(nombre);
            documento.add(new Paragraph(" "));
            documento.add(instrucciones);
            documento.add(new Paragraph(" "));
            documento.add(folioCENEVAL);
            documento.add(fechaExamenes);
            documento.add(lugarYhora);
            documento.add(pagWeb);
            documento.add(diaResultados);
            documento.add(new Paragraph(" "));
            documento.add(ultimo);

            documento.close();
        } catch (DocumentException ex) {
            Logger.getLogger(CrearPDF_Ficha.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String convertir(String fecha) {
        StringTokenizer st = new StringTokenizer(fecha, "-");

        String anio = st.nextToken();
        String mes = st.nextToken();
        String dia = st.nextToken();

        mes = compararMes(Integer.parseInt(mes));

        String fechaCon = dia + " DE " + mes + " DE " + anio;

        return fechaCon;
    }

    public String compararMes(int mes) {
        String letraMes = "error";
        switch (mes) {
            case 1:
                letraMes = "ENERO";
                break;
            case 2:
                letraMes = "FEBRERO";
                break;
            case 3:
                letraMes = "MARZO";
                break;
            case 4:
                letraMes = "ABRIL";
                break;
            case 5:
                letraMes = "MAYO";
                break;
            case 6:
                letraMes = "JUNIO";
                break;
            case 7:
                letraMes = "JULIO";
                break;
            case 8:
                letraMes = "AGOSTO";
                break;
            case 9:
                letraMes = "SEPTIEMBRE";
                break;
            case 10:
                letraMes = "OCTUBRE";
                break;
            case 11:
                letraMes = "NOVIEMBRE";
                break;
            case 12:
                letraMes = "DICIEMBRE";
                break;
        }
        return letraMes;
    }

    public static void drawRectangle(PdfContentByte content, float x, float y, float width, float height) {
        try {

            content.saveState();

            PdfGState state = new PdfGState();
            content.setGState(state);
            content.setRGBColorFill(232, 232, 232);
            content.setColorStroke(BaseColor.BLUE);
            content.setLineWidth((float) .5);
            content.rectangle(x, y, width, height);
            content.fillStroke();
            content.restoreState();

            BaseFont bf = BaseFont.createFont();
            float fontSize = 15f;
            Phrase phrase = new Phrase("Foto", new Font(bf, fontSize));
            ColumnText.showTextAligned(content, Element.ALIGN_CENTER, phrase, 475, 687, 0);
        } catch (DocumentException ex) {
            Logger.getLogger(CrearPDF_Ficha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CrearPDF_Ficha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
