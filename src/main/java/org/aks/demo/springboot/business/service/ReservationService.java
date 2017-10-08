package org.aks.demo.springboot.business.service;

import org.aks.demo.springboot.business.domain.RoomReservation;
import org.aks.demo.springboot.data.entity.Guest;
import org.aks.demo.springboot.data.entity.Reservation;
import org.aks.demo.springboot.data.entity.Room;
import org.aks.demo.springboot.data.repository.GuestRepository;
import org.aks.demo.springboot.data.repository.ReservationRepository;
import org.aks.demo.springboot.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by aksma on 10/8/2017.
 */
@Service
public class ReservationService {
    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;
    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date){
        List<RoomReservation> roomReservations = new ArrayList<>();
        Iterable<Room> rooms = roomRepository.findAll();
        Map<Long,RoomReservation> roomReservationMap = new HashMap<>();

        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(roomReservation.getRoomId(), roomReservation);
        });


       Iterable<Reservation> reservations = reservationRepository.findByDate(new java.sql.Date(date.getTime()));
       if(reservations!=null){
           reservations.forEach(reservation -> {
               Guest guest = guestRepository.findOne(reservation.getGuestId());
               if(guest!=null){
                   RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
                   roomReservation.setDate(date);
                   roomReservation.setGuestId(guest.getId());
                   roomReservation.setFirstName(guest.getFirstName());
                   roomReservation.setLastName(guest.getLastName());
               }
           });
       }
        roomReservations.addAll(roomReservationMap.values());
        return  roomReservations;
    }
}
