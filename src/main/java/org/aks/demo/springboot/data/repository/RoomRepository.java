package org.aks.demo.springboot.data.repository;

import org.aks.demo.springboot.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by aksma on 10/7/2017.
 */
@Repository
public interface RoomRepository extends CrudRepository<Room,Long> {
    Room findByNumber(String number);
}
