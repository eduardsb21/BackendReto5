/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Reto5.Reto5.controller;


import com.Reto5.Reto5.entity.Admin;
import com.Reto5.Reto5.entity.Admin;
import com.Reto5.Reto5.service.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eduar
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/Admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @GetMapping("/all")
    public List<Admin> findAllAdmins() {
        return service.getAdmins();
    }

    @GetMapping("/{id}")
    public Admin findAdminId(@PathVariable int id) {
        return service.getAdminById(id);
    }

    @PostMapping("/save")
    public ResponseEntity addAdmin(@RequestBody Admin admin) {
        service.saveAdmin(admin);
        return ResponseEntity.status(201).build();
    }
    
    @PutMapping("/update")
    public ResponseEntity updateAdmin(@RequestBody Admin admin) {
        service.updateAdmin(admin);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAdminId(@PathVariable int id) {
        service.deleteAdmin(id);
        return ResponseEntity.status(204).build();

    }

}
