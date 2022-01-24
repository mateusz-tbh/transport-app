package io.github.mateusztbh.transportappv3;

import io.github.mateusztbh.transportappv3.Card.Card;
import io.github.mateusztbh.transportappv3.Card.CardRepository;
import io.github.mateusztbh.transportappv3.Counters.Counters;
import io.github.mateusztbh.transportappv3.Counters.CountersRepository;
import io.github.mateusztbh.transportappv3.Fuel.Fuel;
import io.github.mateusztbh.transportappv3.Fuel.FuelRepository;
import io.github.mateusztbh.transportappv3.Trip.Trip;
import io.github.mateusztbh.transportappv3.Trip.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);
    @Autowired
    private final CardRepository cardRepository;
    @Autowired
    private final CountersRepository countersRepository;
    @Autowired
    private final FuelRepository fuelRepository;
    @Autowired
    private final TripRepository tripRepository;

    AppController(final CardRepository cardRepository, final CountersRepository countersRepository, final FuelRepository fuelRepository, final TripRepository tripRepository) {
        this.cardRepository = cardRepository;
        this.countersRepository = countersRepository;
        this.fuelRepository = fuelRepository;
        this.tripRepository = tripRepository;
    }

    @GetMapping("")
    public String listCards(Model model) {
        List<Card> listCards = cardRepository.findAll();
        model.addAttribute("listCards", listCards);
        return "index";
    }

    @GetMapping("/new")
    public String showCardNewForm(Model model) {
        model.addAttribute("card", new Card());
        return "card_form";
    }

    @PostMapping("/save")
    public String saveCard(@Valid Card card, Model model) {
        if(cardRepository.existsByNumber(card.getNumber())) {
            model.addAttribute("message", "Karta o danym numerze istnieje");
            return "card_form";
        } else {
            cardRepository.save(card);
            countersRepository.save(new Counters(card, 0, 0, 0, 0));
            return "redirect:/";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCard(@PathVariable("id") Integer id, Model model) {
        cardRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/pdf/{id}")
    public void exportToPdf(@PathVariable("id") Integer id, HttpServletResponse response, Model model) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd__HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=card_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        Card number = cardRepository.getById(id);
        var result = number.getNumber();

        List<Trip> trips = tripRepository.findALlByCard_Id(id);
        List<Fuel> fuels = fuelRepository.findAllByCard_Id(id);
        List<Counters> counters = countersRepository.findALlByCard_Id(id);

        PdfExporter exporter = new PdfExporter(result, trips, fuels, counters);

        exporter.export(response);
    }
}
