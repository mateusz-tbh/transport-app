package io.github.mateusztbh.transportappv3.Fuel;

import io.github.mateusztbh.transportappv3.Card.Card;
import io.github.mateusztbh.transportappv3.Card.CardRepository;
import io.github.mateusztbh.transportappv3.Counters.CountersRepository;
import io.github.mateusztbh.transportappv3.Trip.Trip;
import io.github.mateusztbh.transportappv3.Trip.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FuelController {
    private static final Logger logger = LoggerFactory.getLogger(FuelController.class);
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private FuelRepository fuelRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private CountersRepository countersRepository;

    @GetMapping("/fuel/new")
    public String showNewFuelForm(Model model) {
        List<Card> listCards = cardRepository.findAll();

        model.addAttribute("fuel", new Fuel());
        model.addAttribute("listCards", listCards);

        return "fuel_form";
    }

    @GetMapping("/fuel/new/{id}")
    public String showNewFuelFormByCard_Id(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("fuels", fuelRepository.findAll());

        Card listCards = cardRepository.findById(id).get();

        model.addAttribute("fuel", new Fuel());
        model.addAttribute("listCards", listCards);

        return "fuel_form_by_card";
    }

    @PostMapping("/fuel/save")
    public String saveFuel(Fuel fuel) {
        fuelRepository.save(fuel);

        return "redirect:/fuels";
    }

    @PostMapping("/fuel/save/{id}")
    public String saveFuelByCard_Id(@RequestParam(value = "id", required = false) Integer id, Fuel fuel) {
        fuelRepository.save(fuel);
        var result = fuel.getCard().getId();
        logger.info("no params");

        return "redirect:/list/" + result;
    }

    @PostMapping(params = "addFuel", value = "/fuel/save/{id}")
    public String addFuelByCard_Id(@RequestParam(value = "id", required = false) Integer id, Fuel fuel) {
        var result = fuel.getCard().getId();
        fuelRepository.save(fuel);
        logger.info("params");
        return "redirect:/fuel/new/" + result;
    }

    @GetMapping("/fuel/result/{id}")
    public String sumFuel(@PathVariable("id") Integer id) {
        Fuel fuel = fuelRepository.findById(id).get();
        fuel.setRefuelingSum(fuelRepository.sumFuel());
        fuelRepository.save(fuel);

        var result = fuel.getCard().getId();

        return "redirect:/list/" + result;
    }

    @GetMapping("/fuels")
    public String listFuels(Model model) {
        List<Fuel> listFuels = fuelRepository.findAll();
        model.addAttribute("listFuels", listFuels);

        return "fuels";
    }

    @GetMapping("/fuel/edit/{id}")
    public String showEditFuelForm(@PathVariable("id") Integer id, Model model) {
        Fuel fuel = fuelRepository.findById(id).get();
        model.addAttribute("fuel", fuel);

        List<Card> listCards = cardRepository.findAll();
        model.addAttribute("listCards", listCards);

        return "fuel_form_by_card";
    }

    @GetMapping("/fuel/delete/{id}")
    public String deleteFuel(@PathVariable("id") Integer id, Model model) {
        var result = fuelRepository.findById(id).get().getCard().getId();
        fuelRepository.deleteById(id);

        return "redirect:/list/" + result;
    }

    @ModelAttribute("fuels")
    List<Fuel> getFuel() {
        return fuelRepository.findAll();
    }
}
