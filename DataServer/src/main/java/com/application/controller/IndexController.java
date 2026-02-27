package com.application.controller;

import com.application.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping()
    public String getIndex(Model model) {
        model.addAttribute("ticketList", ticketRepository.findAll());
        return "index";
    }

}
