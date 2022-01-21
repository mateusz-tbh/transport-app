package io.github.mateusztbh.transportappv3.Card;

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

import java.util.List;

@Controller
public class CardController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);
    @Autowired
    private final CardRepository cardRepository;
    @Autowired
    private final TripRepository tripRepository;
    @Autowired
    private final FuelRepository fuelRepository;
    @Autowired
    private final CountersRepository countersRepository;

    public CardController(final CardRepository cardRepository, final TripRepository tripRepository, final FuelRepository fuelRepository, final CountersRepository countersRepository) {
        this.cardRepository = cardRepository;
        this.tripRepository = tripRepository;
        this.fuelRepository = fuelRepository;
        this.countersRepository = countersRepository;
    }

    /**Listing all created cards */
    /*@GetMapping("/cards")
    public String listCards(Model model) {
        List<Card> listCards = cardRepository.findAll();
        model.addAttribute("listCards", listCards);
        return "cards";
    }*/

    /**Returning form to add new card */
    /*@GetMapping("/cards/new")
    public String showCardNewForm(Model model) {
        model.addAttribute("card", new Card());
        return "card_form";
    }*/

    /**Adding new card */
    /*@PostMapping("/card/save")
    public String saveCard(Card card) {
        cardRepository.save(card);
        return "redirect:/cards";
    }*/

    @GetMapping("/counters/update/{id}")
    public String updateDataFromCard(@PathVariable("id") Integer id, Model model) {
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
    }

    /**Returning all data from card */
    @GetMapping("/list/{id}")
    public String showTripsInCard(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("trips", tripRepository.findALlByCard_Id(id));
        model.addAttribute("fuels", fuelRepository.findALlByCard_Id(id));
        model.addAttribute("counters", countersRepository.findALlByCard_Id(id));

        return "card-trips";
    }

    /** Show form to add new trip but with ID of chosen card */
    /*@GetMapping("/trips/new/{id}")
    public String showNewTripForm(@PathVariable("id") Integer id, Model model) {
//        List<Card> listCards = cardRepository.findAll();
        Card card = cardRepository.findById(id).get();
        Trip tripId = tripRepository.findByCard_Id(id).get(id);

        model.addAttribute("trip", new Trip());
//        model.addAttribute("listCards", listCards);
        model.addAttribute("tripId", tripId);

        return "trip_form";
    }*/

    /*@GetMapping("/delete/{id}")
    public String deleteCard(@PathVariable("id") Integer id, Model model) {
        cardRepository.deleteById(id);
        return "redirect:/cards";
    }*/
}
