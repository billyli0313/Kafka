package com.example.concur.controller;

import com.example.concur.entity.Company;
import com.example.concur.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @PostMapping(value = "/create/company/table")
    public void createCompanyTable(@RequestParam(value = "id")String id, @RequestParam(value = "name")String name,
                                   @RequestParam(value = "catchPhrase")String catchPhrase, @RequestParam(value = "bs")String bs)
            throws HttpClientErrorException {
        if(id.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Company ID is Empty!");
        }
        if(companyService.getCompanyById(id) != null){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Company exists, cannot create again!");
        }
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setCatchPhrase(catchPhrase);
        company.setBs(bs);
        companyService.saveCompany(company);
    }
    @PutMapping(value = "/update/company/table")
    public void updateCompanyTable(@RequestParam(value = "id")String id, @RequestParam(value = "name")String name,
                                   @RequestParam(value = "catchPhrase")String catchPhrase, @RequestParam(value = "bs")String bs)
            throws HttpClientErrorException {
        if(id.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Company ID is Empty!");
        }
        Company company = companyService.getCompanyById(id);
        if(company == null){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Please create company: "+ id + " first!");
        }
        company.setName(name);
        company.setCatchPhrase(catchPhrase);
        company.setBs(bs);
        companyService.saveCompany(company);
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
