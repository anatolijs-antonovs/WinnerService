package com.app.controller;

import com.app.entity.Winner;
import com.app.service.WinnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/winners")
public class WinnerController {

    @Autowired
    private WinnerService winnerService;

    @GetMapping("greeting")
    public String greeting() {
        return "greetings";
    }

    @Operation(summary = "Add winner with win amount to the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Winner added")
    })
    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity add(@RequestBody Winner winner) {
        winnerService.addWin(winner);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Add winner with win amount to the database")
    @GetMapping(value = "/top", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List> topWinners() {        ;
        return new ResponseEntity<>(winnerService.getTopWinners(), HttpStatus.OK);
    }
}
