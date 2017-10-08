package org.aks.demo.springboot.web.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by aksma on 10/8/2017.
 */
@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {

    @RequestMapping(method=RequestMethod.GET)
    public String getReservations(){
        return "reservations";
    }
}
