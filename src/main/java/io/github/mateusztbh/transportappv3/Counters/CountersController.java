package io.github.mateusztbh.transportappv3.Counters;

import io.github.mateusztbh.transportappv3.Card.Card;
import io.github.mateusztbh.transportappv3.Card.CardRepository;
import io.github.mateusztbh.transportappv3.Fuel.FuelRepository;
import io.github.mateusztbh.transportappv3.Trip.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CountersController {
    private static final Logger logger = LoggerFactory.getLogger(CountersController.class);
    private TripRepository tripRepository;
    private FuelRepository fuelRepository;
    private CountersRepository countersRepository;
    private CardRepository cardRepository;

    public CountersController(final TripRepository tripRepository, final FuelRepository fuelRepository, final CountersRepository countersRepository, final CardRepository cardRepository) {
        this.tripRepository = tripRepository;
        this.fuelRepository = fuelRepository;
        this.countersRepository = countersRepository;
        this.cardRepository = cardRepository;
    }

    @GetMapping("/counters/new")
    public String showCountersForm(Model model, Integer id) {
        List<Card> listCards = cardRepository.findAll();

        model.addAttribute("counters", new Counters());
        model.addAttribute("listCards", listCards);

        return "counters_form";
    }

    @PostMapping("/counters/save")
    public String saveCounters(Counters counters) {
        countersRepository.save(counters);

        return "redirect:/counters";
    }

    @GetMapping("/counters")
    public String listCounters(Model model) {
        List<Counters> listCounters = countersRepository.findAll();
        model.addAttribute("listCounters", listCounters);

        return "counters";
    }

    /*@GetMapping("/counters/update/{id}")
    public String testId(@PathVariable("id") Integer id, Model model) {
        List<Trip> listTrips = tripRepository.findALlByCard_Id(id);
        var variableMin = listTrips.stream()
                .mapToInt(Trip::getDepartureCounter)
                .min();
        var variableMax = listTrips.stream()
                .mapToInt(Trip::getArrivalCounter)
                .max();

        var course = listTrips.stream()
                .mapToInt(Trip::getCourse).sum();

        List<Fuel> listFuels = fuelRepository.findALlByCard_Id(id);
        var sumFuel = listFuels.stream()
                .mapToInt(Fuel::getRefuelingQuantity).sum();

        Counters counters = countersRepository.findByCard_Id(id);
        counters.setCounterStart(variableMin.orElse(0));
        counters.setCounterEnd(variableMax.orElse(0));
        counters.setCounterCourse(course);
        counters.setCounterFuel(sumFuel);
        countersRepository.save(counters);

        model.addAttribute("trips", tripRepository.findALlByCard_Id(id));
        model.addAttribute("fuels", fuelRepository.findALlByCard_Id(id));
        model.addAttribute("counters", countersRepository.findALlByCard_Id(id));

        return "card-trips";
    }*/

    @GetMapping("/counters/delete/{id}")
    public String deleteCounter(@PathVariable("id") Integer id, Model model) {
        countersRepository.deleteById(id);
        var result = countersRepository.findByCard_Id(id);

        return "redirect:/";
    }
}
