package com.example.concur.controller;

import com.example.concur.entity.Geography;
import com.example.concur.service.GeographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@RestController
public class GeographyController {
    @Autowired
    private GeographyService geographyService;
    @PostMapping(value = "/create/geography/table")
    public void createGeographyTable(@RequestParam(value = "id")String id, @RequestParam(value = "lat")String lat,
                                     @RequestParam(value = "lng")String lng)
            throws HttpClientErrorException {
        if(id.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Geography ID is Empty!");
        }
        if(geographyService.getGeographyById(id) != null){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Geography exists, cannot create again!");
        }
        Geography geography = new Geography();
        geography.setId(id);
        geography.setLat(lat);
        geography.setLng(lng);
        geographyService.saveGeography(geography);

    }
    @PutMapping(value = "/update/geography/table")
    public void updateGeographyTable(@RequestParam(value = "id")String id, @RequestParam(value = "lat")String lat,
                                   @RequestParam(value = "lng")String lng)
            throws HttpClientErrorException {
        if(id.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Geography ID is Empty!");
        }
        Geography geography = geographyService.getGeographyById(id);
        if(geography == null){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Please create geography: "+ id + " first!");
        }
        geography.setLat(lat);
        geography.setLng(lng);
        geographyService.saveGeography(geography);

    }
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleHttpClientErrorException(HttpClientErrorException e){
        return "Bad entity. Error message: " + e.getMessage();
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleExceptions(Exception e){
        return "Sorry, there is an issue: " + e.getMessage();
    }
}
