package com.application.controller;

import com.application.model.Ticket;
import com.application.model.indicators.*;
import com.application.repository.*;
import org.apache.coyote.BadRequestException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private IndicatorsDebtRepository indicatorsDebtRepository;

    @Autowired
    private IndicatorsEfficiencyRepository indicatorsEfficiencyRepository;

    @Autowired
    private IndicatorsGrowthRepository indicatorsGrowthRepository;

    @Autowired
    private IndicatorsProfitRepository indicatorsProfitRepository;

    @Autowired
    private IndicatorsValuationRepository indicatorsValuationRepository;

    // When a day range PathParam is not passed, assume a default range of 30 days
    // Meaning the last 30 days of data will be queried
    // 30 is assumed as it's just enough data for visualization
    private final Integer DEFAULT_DAY_RANGE = 30;

    @GetMapping("/ticker")
    @ResponseBody
    public ResponseEntity<List<Ticket>> getAllTickers() {

        // TODO - add DTO or VO instead of entity

        List<Ticket> tickets = ticketRepository.findAll();

        return ResponseEntity.ok(tickets);
    }

    @GetMapping(value = {"/market-data/debt/{ticket-name}", "/market-data/debt/{ticket-name}/{dayRange}"})
    @ResponseBody
    public ResponseEntity<List<IndicatorsDebt>> getDebtByTicket(@PathVariable("ticket-name") String ticketName,
                                                                @PathVariable Optional<Integer> dayRange)
            throws BadRequestException {

        if (Strings.isEmpty(ticketName)) {
            throw new BadRequestException("Expecting a ticket identifier on the path, such as /market-data/debt/[ticket]/[day range]");
        }

        if (dayRange.isEmpty()) {
            dayRange = Optional.of(DEFAULT_DAY_RANGE);
        }

        if (dayRange.get() < 1) {
            throw new BadRequestException("Expecting a day range value above 0 on the path, such as /market-data/debt/[ticket]/[day range]");
        }

        // TODO - add DTO or VO instead of entity
        // TODO - add control for the day range

        // Default range in days to query the data
        List<IndicatorsDebt> data = indicatorsDebtRepository.findAllByTicketAndRange(ticketName, dayRange.get());

        return ResponseEntity.ok(data);
    }

    @GetMapping(value = {"/market-data/efficiency/{ticket-name}", "/market-data/efficiency/{ticket-name}/{dayRange}"})
    @ResponseBody
    public ResponseEntity<List<IndicatorsEfficiency>> getEfficiencyByTicket(@PathVariable("ticket-name") String ticketName,
                                                                            @PathVariable Optional<Integer> dayRange)
            throws BadRequestException {

        if (Strings.isEmpty(ticketName)) {
            throw new BadRequestException("Expecting a ticket identifier on the path, such as /market-data/efficiency/[ticket]/[day range]");
        }

        if (dayRange.isEmpty()) {
            dayRange = Optional.of(DEFAULT_DAY_RANGE);
        }

        if (dayRange.get() < 1) {
            throw new BadRequestException("Expecting a day range value above 0 on the path, such as /market-data/efficiency/[ticket]/[day range]");
        }

        // TODO - check if Ticket exists
        // TODO - add DTO or VO instead of entity
        // TODO - add control for the day range

        List<IndicatorsEfficiency> data = indicatorsEfficiencyRepository.findAllByTicketAndRange(ticketName, dayRange.get());

        return ResponseEntity.ok(data);
    }

    @GetMapping(value = {"/market-data/growth/{ticket-name}", "/market-data/growth/{ticket-name}/{dayRange}"})
    @ResponseBody
    public ResponseEntity<List<IndicatorsGrowth>> getGrowthByTicket(@PathVariable("ticket-name") String ticketName,
                                                                    @PathVariable Optional<Integer> dayRange)
            throws BadRequestException {

        if (Strings.isEmpty(ticketName)) {
            throw new BadRequestException("Expecting a ticket identifier on the path, such as /market-data/growth/[ticket]/[day range]");
        }

        if (dayRange.isEmpty()) {
            dayRange = Optional.of(DEFAULT_DAY_RANGE);
        }

        if (dayRange.get() < 1) {
            throw new BadRequestException("Expecting a day range value above 0 on the path, such as /market-data/growth/[ticket]/[day range]");
        }

        // TODO - check if Ticket exists
        // TODO - add DTO or VO instead of entity
        // TODO - add control for the day range

        List<IndicatorsGrowth> data = indicatorsGrowthRepository.findAllByTicketAndRange(ticketName, dayRange.get());

        return ResponseEntity.ok(data);
    }

    @GetMapping(value = {"/market-data/profit/{ticket-name}", "/market-data/profit/{ticket-name}/{dayRange}"})
    @ResponseBody
    public ResponseEntity<List<IndicatorsProfit>> getProfitByTicket(@PathVariable("ticket-name") String ticketName,
                                                                    @PathVariable Optional<Integer> dayRange)
            throws BadRequestException {

        if (Strings.isEmpty(ticketName)) {
            throw new BadRequestException("Expecting a ticket identifier on the path, such as /market-data/profit/[ticket]/[day range]");
        }

        if (dayRange.isEmpty()) {
            dayRange = Optional.of(DEFAULT_DAY_RANGE);
        }

        if (dayRange.get() < 1) {
            throw new BadRequestException("Expecting a day range value above 0 on the path, such as /market-data/profit/[ticket]/[day range]");
        }

        // TODO - check if Ticket exists
        // TODO - add DTO or VO instead of entity
        // TODO - add control for the day range

        List<IndicatorsProfit> data = indicatorsProfitRepository.findAllByTicketAndRange(ticketName, dayRange.get());

        return ResponseEntity.ok(data);
    }

    @GetMapping(value = {"/market-data/valuation/{ticket-name}", "/market-data/valuation/{ticket-name}/{dayRange}"})
    @ResponseBody
    public ResponseEntity<List<IndicatorsValuation>> getValuationByTicket(@PathVariable("ticket-name") String ticketName,
                                                                          @PathVariable Optional<Integer> dayRange)
            throws BadRequestException {

        if (Strings.isEmpty(ticketName)) {
            throw new BadRequestException("Expecting a ticket identifier on the path, such as /market-data/valuation/[ticket]/[day range]");
        }

        if (dayRange.isEmpty()) {
            dayRange = Optional.of(DEFAULT_DAY_RANGE);
        }

        if (dayRange.get() < 1) {
            throw new BadRequestException("Expecting a day range value above 0 on the path, such as /market-data/valuation/[ticket]/[day range]");
        }

        // TODO - check if Ticket exists
        // TODO - add DTO or VO instead of entity
        // TODO - add control for the day range

        List<IndicatorsValuation> data = indicatorsValuationRepository.findAllByTicketAndRange(ticketName, dayRange.get());

        return ResponseEntity.ok(data);
    }

}