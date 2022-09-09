/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Reto5.Reto5.repository;

import com.Reto5.Reto5.controller.CountClients;
import com.Reto5.Reto5.entity.Client;
import com.Reto5.Reto5.entity.Reservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eduar
 */
@Repository
public class RepositoryReservation {
    @Autowired
    private ReservationRepository repositorio;
    
    public List<Reservation> getAll(){
        return (List<Reservation>) repositorio.findAll();
    }
    
    public Optional<Reservation> getReservation(int id){
        return repositorio.findById(id);
    }
    
    public Reservation save(Reservation reservation){
        return repositorio.save(reservation);
    }
    
    public void delete(Reservation reservation){
        repositorio.delete(reservation);
    }
    public List<Reservation> ReservacionStatusRepositorio (String status){
         return repositorio.findAllByStatus(status);
     }
     
     public List<Reservation> ReservacionTiempoRepositorio (Date a, Date b){
         return repositorio.findAllByStartDateAfterAndStartDateBefore(a, b);
     
     }
     
     public List<CountClients> getClientesRepositorio(){
         List<CountClients> res = new ArrayList<>();
         List<Object[]> report = repositorio.countTotalReservationsByClient();
         for(int i=0; i<report.size(); i++){
             res.add(new CountClients((Long)report.get(i)[1],(Client) report.get(i)[0]));
         }
         return res;
     }
}
