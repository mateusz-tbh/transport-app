package io.github.mateusztbh.transportappv3.Card;

import io.github.mateusztbh.transportappv3.Trip.Trip;
import io.github.mateusztbh.transportappv3.Trip.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CardController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TripRepository tripRepository;

    @GetMapping("/cards")
    public String listCards(Model model) {
        List<Card> listCards = cardRepository.findAll();
        model.addAttribute("listCards", listCards);
        return "cards";
    }

    @GetMapping("/cards/new")
    public String showCardNewForm(Model model) {
        model.addAttribute("card", new Card());
        return "card_form";
    }

    @PostMapping("/card/save")
    public String saveCard(Card card) {
        cardRepository.save(card);
        return "redirect:/cards";
    }

    @GetMapping("/list/{id}")
    public String showTripsInCard(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("trips", tripRepository.findALlByCard_Id(id));
        return "card-trips";
    }

    @GetMapping("/card/delete/{id}")
    public String deleteCard(@PathVariable("id") Integer id, Model model) {
        cardRepository.deleteById(id);
        return "redirect:/cards";
    }
}
