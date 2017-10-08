package org.aks.demo.springboot.data.webservice;

import org.aks.demo.springboot.data.entity.Room;
import org.aks.demo.springboot.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aksma on 10/7/2017.
 */
@RestController
public class RoomController  {
    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping(value = "/rooms",method = RequestMethod.GET)
    List<Room> findAll(@RequestParam(required = false) String number){
        List<Room> rooms = new ArrayList<>();
        if(number==null){
            Iterable<Room> roomIterable = roomRepository.findAll();
            roomIterable.forEach(room -> {
                rooms.add(room);
            });
        }else{
            Room room = roomRepository.findByNumber(number);
            rooms.add(room);
        }
        return  rooms;
    }
}
