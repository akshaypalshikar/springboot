package org.aks.demo.springboot.web.app;

import org.aks.demo.springboot.business.domain.RoomReservation;
import org.aks.demo.springboot.business.service.ReservationService;
import org.aks.demo.springboot.data.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by aksma on 10/8/2017.
 */
@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {
    private static final DateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd");
    private static  final  Logger logger = Logger.getGlobal();

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method=RequestMethod.GET)
    public String getReservations(@RequestParam(value = "date",required = false)String dateString, Model model){
        Date date;
        if(dateString!=null){
            try {
                date = DATE_FORMAT.parse(dateString);
            } catch (ParseException e) {
                logger.log(Level.SEVERE,"input date for mat is not correct",e);
                date = new Date();
            }
        }else {
            date = new Date();
        }
        List<RoomReservation> roomReservations = reservationService.getRoomReservationsForDate(date);
        model.addAttribute("roomReservations",roomReservations);
        return "reservations";
    }
}
