package com.hasanboy.Atto.controller;

import com.hasanboy.Atto.model.Terminal;
import com.hasanboy.Atto.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/terminal")
public class TerminalController {

    @Autowired
    TerminalService terminalService;

    @PostMapping("/create/terminal")
    public ResponseEntity<?> createCard(@RequestBody Terminal terminal) {
        Terminal result = terminalService.create(terminal);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/add/balance")
    public ResponseEntity<?> addBalance(@RequestParam("n") String number,
                                        @RequestParam("c") BigDecimal balance){
        return ResponseEntity.ok(terminalService.balance(number,balance));
    }


    @GetMapping("/getByTerminal/{number}")
    public ResponseEntity<?> getByNumber(@PathVariable("number") String number){
        Terminal result = terminalService.getByNumber(number);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        Terminal result = terminalService.getById(id);
        return ResponseEntity.ok(result);
    }

}
