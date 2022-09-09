/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Reto5.Reto5.service;


import com.Reto5.Reto5.entity.Admin;
import com.Reto5.Reto5.repository.AdminRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eduar
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository repository;

    public List<Admin> getAdmins() {
        return repository.findAll();
    }

    public Admin getAdminById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Admin saveAdmin(Admin admin) {
        return repository.save(admin);
    }
    
    public Admin updateAdmin(Admin admin){
        Admin existeAdmin = getAdminById(admin.getId());
        existeAdmin.setId(admin.getId());
        //existeAdmin.setEmail(admin.getEmail());
        existeAdmin.setName(admin.getName());
        existeAdmin.setPassword(admin.getPassword());
        return repository.save(existeAdmin);
    }
    
    public void deleteAdmin(int id){
        repository.deleteById(id);
    }
}
