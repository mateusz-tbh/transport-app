package io.github.mateusztbh.transportappv3;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import io.github.mateusztbh.transportappv3.Counters.Counters;
import io.github.mateusztbh.transportappv3.Fuel.Fuel;
import io.github.mateusztbh.transportappv3.Trip.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PdfExporter {

    private static final Logger logger = LoggerFactory.getLogger(PdfExporter.class);
    private String number;
    private List<Trip> listTrips;
    private List<Fuel> listFuels;
    private List<Counters> listCounters;

    PdfExporter(String number, final List<Trip> listTrips, final List<Fuel> listFuels, final List<Counters> listCounters) {
        this.number = number;
        this.listTrips = listTrips;
        this.listFuels = listFuels;
        this.listCounters = listCounters;
    }

    private void writeTableHeaderTrip(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.blue);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Dzien", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Godzina", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Miejscowosc", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Kraj", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Licznik", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Dzien", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Godzina", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Miejscowosc", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Kraj", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Licznik", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Przebieg", font));
        table.addCell(cell);
    }

    private void writeTableHeaderFuel (PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.blue);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Data", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Miejscowosc", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Licznik", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ilosc tankowania", font));
        table.addCell(cell);
    }

    private void writeTableHeaderCounters (PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.blue);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Wyjazd", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Przyjazd", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Przebieg", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Suma tankowania", font));
        table.addCell(cell);
    }

    private void writeTableDataTrip(PdfPTable table) {
        for(Trip trips : listTrips) {
            table.addCell(trips.getDepartureDay());
            table.addCell(trips.getDepartureClock());
            table.addCell(trips.getDepartureLocation());
            table.addCell(trips.getDepartureCountry());
            table.addCell(String.valueOf(trips.getDepartureCounter()));

            table.addCell(trips.getArrivalDay());
            table.addCell(trips.getArrivalClock());
            table.addCell(trips.getArrivalLocation());
            table.addCell(trips.getArrivalCountry());
            table.addCell(String.valueOf(trips.getArrivalCounter()));

            table.addCell(String.valueOf(trips.getCourse()));
        }
    }

    private void writeTableDataFuel (PdfPTable table) {
        for(Fuel fuels : listFuels) {
            table.addCell(fuels.getFuelDate());
            table.addCell(fuels.getFuelLocation());
            table.addCell(String.valueOf(fuels.getFuelCounter()));
            table.addCell(String.valueOf(fuels.getRefuelingQuantity()));
        }
    }

    private void writeTableDataCounters (PdfPTable table) {
        for(Counters counters : listCounters) {
            table.addCell(String.valueOf(counters.getCounterStart()));
            table.addCell(String.valueOf(counters.getCounterEnd()));
            table.addCell(String.valueOf(counters.getCounterCourse()));
            table.addCell(String.valueOf(counters.getCounterFuel()));
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLUE);
        font.setSize(18);

        Paragraph title = new Paragraph("Numer karty: " + number, font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable tableTrip = new PdfPTable(11);
        tableTrip.setWidthPercentage(100);
        tableTrip.setSpacingBefore(15);
        tableTrip.setWidths(new float[] {1.5f, 2.5f, 2.5f, 1f, 1.5f, 1.5f, 2.5f, 2.5f, 1f, 1.5f, 2f});

        writeTableHeaderTrip(tableTrip);
        writeTableDataTrip(tableTrip);

        PdfPTable tableFuel = new PdfPTable(4);
        tableFuel.setWidthPercentage(100);
        tableFuel.setSpacingBefore(15);
        tableFuel.setWidths(new float[] {1.5f, 2.5f, 2.0f, 2.5f});

        writeTableHeaderFuel(tableFuel);
        writeTableDataFuel(tableFuel);

        PdfPTable tableCounters = new PdfPTable(4);
        tableCounters.setWidthPercentage(100);
        tableCounters.setSpacingBefore(15);
        tableCounters.setWidths(new float[] {1.5f, 2.5f, 2.0f, 2.5f});

        writeTableHeaderCounters(tableCounters);
        writeTableDataCounters(tableCounters);

        document.add(tableTrip);
        document.add(tableFuel);
        document.add(tableCounters);

        document.close();
    }

}
