package io.github.mateusztbh.transportappv3;

import io.github.mateusztbh.transportappv3.Card.Card;
import io.github.mateusztbh.transportappv3.Card.CardController;
import io.github.mateusztbh.transportappv3.Card.CardRepository;
import io.github.mateusztbh.transportappv3.Counters.Counters;
import io.github.mateusztbh.transportappv3.Counters.CountersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CountersRepository countersRepository;

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
    public String saveCard(Card card) {
        var result = card.getId();
        logger.info(String.valueOf(result));
        cardRepository.save(card);
        countersRepository.save(new Counters(card,0, 0, 0 ,0));
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCard(@PathVariable("id") Integer id, Model model) {
        cardRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/test/{id}")
    public String test(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("cards", cardRepository.findAllById(Collections.singleton(id)));
        return "test";
    }
}
