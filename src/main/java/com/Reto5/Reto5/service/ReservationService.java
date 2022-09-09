/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Reto5.Reto5.service;


import com.Reto5.Reto5.controller.CountClients;
import com.Reto5.Reto5.controller.StatusReservas;
import com.Reto5.Reto5.entity.Reservation;
import com.Reto5.Reto5.repository.RepositoryReservation;
import com.Reto5.Reto5.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eduar
 */
@Service
public class ReservationService {

    /**
     * Instancia del repositorio
     */
    @Autowired
    private ReservationRepository repository;
    @Autowired
    private RepositoryReservation operCrud;

    /**
     * Metodo para obtener las reservaciones
     *
     * @return
     */
    public List<Reservation> getReservations() {
        return repository.findAll();
    }

    /**
     * metodo para obtener una reservación por id
     *
     * @param id
     * @return
     */
    public Reservation getReservationById(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * metodo para guardar una reservación
     *
     * @param reservation
     * @return
     */
    public Reservation saveReservation(Reservation reservation) {
        return repository.save(reservation);
    }

    /**
     * metodo para actualizar una reservación
     *
     * @param reservation
     * @return
     */
    public Reservation updateReservation(Reservation reservation) {
        Reservation existReservation = getReservationById(reservation.getIdReservation());
        existReservation.setIdReservation(reservation.getIdReservation());
        existReservation.setStartDate(reservation.getStartDate());
        existReservation.setDevolutionDate(reservation.getDevolutionDate());
        existReservation.setStatus(reservation.getStatus());
        return repository.save(existReservation);

    }

    /**
     * metodo para eliminar una reservación
     *
     * @param id
     */
    public void deleteReservation(int id) {
        repository.deleteById(id);
    }
    
    
    
   
    
    public List<Reservation> reportTimeService (String datoA, String datoB){
        SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");
        
        Date datoUno = new Date();
        Date datoDos = new Date();
        
        try{
             datoUno = parser.parse(datoA);
             datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return operCrud.ReservacionTiempoRepositorio(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        
        } 
    }
    
    public List<CountClients> reportClientService(){
            return operCrud.getClientesRepositorio();
        }
    
    public StatusReservas reportStatusService (){
        List<Reservation>completed= operCrud.ReservacionStatusRepositorio("completed");
        List<Reservation>cancelled= operCrud.ReservacionStatusRepositorio("cancelled");
        
        return new StatusReservas(completed.size(), cancelled.size() );
    }
        
}

