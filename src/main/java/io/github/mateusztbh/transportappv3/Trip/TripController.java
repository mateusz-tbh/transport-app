package io.github.mateusztbh.transportappv3.Trip;

import io.github.mateusztbh.transportappv3.Card.Card;
import io.github.mateusztbh.transportappv3.Card.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TripController {
    private static final Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    private final TripRepository tripRepository;
    @Autowired
    private final CardRepository cardRepository;

    public TripController(final TripRepository tripRepository, final CardRepository cardRepository) {
        this.tripRepository = tripRepository;
        this.cardRepository = cardRepository;
    }

    @GetMapping("/trips/new/{id}")
    public String showNewTripFormByCard_Id(@PathVariable("id") Integer id, Model model) {

        Card listCards = cardRepository.findById(id).get();

        model.addAttribute("trip", new Trip());
        model.addAttribute("listCards", listCards);

        return "trip_form_by_card";
    }

    @PostMapping("/trip/save")
    public String saveTrip( Trip trip) {
        trip.setCourse(trip.subtract(trip));
        tripRepository.save(trip);

        return "redirect:/";
    }

    @PostMapping("/trip/save/{id}")
    public String saveTripByCard_Id(@RequestParam(value = "id", required = false) Integer id, Trip trip) {
        trip.setCourse(trip.subtract(trip));
        tripRepository.save(trip);
        var result = trip.getCard().getId();

        return "redirect:/list/" + result;
    }

    /** Not completed feature **/
    @PostMapping(params = "addTrip", name = "/trip/save/{id}")
    public String addTripByCard_Id(@RequestParam(value = "id", required = false) Integer id, Trip trip, Card card) {
        card.getTrip().add(new Trip());
        return "trip_form_by_card";
    }

    @GetMapping("/trip/edit/{id}")
    public String showEditTripForm(@PathVariable("id") Integer id, Model model) {
        Trip trip = tripRepository.findById(id).get();
        trip.setCourse(trip.subtract(trip));
        model.addAttribute("trip", trip);

        List<Card> listCards = cardRepository.findAll();
        model.addAttribute("listCards", listCards);

        return "trip_form_by_card";
    }

    @GetMapping("/trip/delete/{id}")
    public String deleteTrip(@PathVariable("id") Integer id, Model model) {
        var result = tripRepository.findById(id).get().getCard().getId();
        logger.info(String.valueOf(result));
        tripRepository.deleteById(id);
        return "redirect:/list/" + result;
    }
}
