package io.github.mateusztbh.transportappv3.Trip;

import io.github.mateusztbh.transportappv3.Card.Card;
import io.github.mateusztbh.transportappv3.Card.CardRepository;
import io.github.mateusztbh.transportappv3.Counters.CountersController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TripController {
    private static final Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/trips/new")
    public String showNewTripForm(Model model) {
        List<Card> listCards = cardRepository.findAll();

        model.addAttribute("trip", new Trip());
        model.addAttribute("listCards", listCards);

        return "trip_form";
    }

    @GetMapping("/trips/new/{id}")
    public String showNewTripFormByCard_Id(@PathVariable("id") Integer id, Model model) {

        Card listCards = cardRepository.findById(id).get();

        model.addAttribute("trip", new Trip());
        model.addAttribute("listCards", listCards);

        return "trip_form";
    }

    @PostMapping("/trip/save")
    public String saveTrip( Trip trip) {
        trip.setCourse(trip.subtract(trip));
        tripRepository.save(trip);

        return "redirect:/";
    }

    @GetMapping("/trips")
    public String listTrips(Model model) {
        List<Trip> listTrips = tripRepository.findAll();
        model.addAttribute("listTrips", listTrips);

        return "trips";
    }

    @GetMapping("/trip/edit/{id}")
    public String showEditTripForm(@PathVariable("id") Integer id, Model model) {
        Trip trip = tripRepository.findById(id).get();
        trip.setCourse(trip.subtract(trip));
        model.addAttribute("trip", trip);

        List<Card> listCards = cardRepository.findAll();
        model.addAttribute("listCards", listCards);

        return "trip_form";
    }

    @GetMapping("/trip/delete/{id}")
    public String deleteTrip(@PathVariable("id") Integer id, Model model) {
        tripRepository.deleteById(id);
        return "redirect:/trips";
    }
}
