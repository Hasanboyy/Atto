package com.hasanboy.Atto.controller;

import com.Hibrnate.Atto.model.Transaction;
import com.Hibrnate.Atto.service.TransuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transuction")
public class TransuctionController {

   @Autowired
    TransuctionService transuctionService;

    @PostMapping("/create/transaction")
    public ResponseEntity<?> add(@RequestBody Transaction transaction){
        Transaction result = transuctionService.create(transaction);
        return ResponseEntity.ok(result);
    }

   @GetMapping("/get/transaction/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
       Transaction result = transuctionService.getById(id);
       return ResponseEntity.ok(result);
   }
}
