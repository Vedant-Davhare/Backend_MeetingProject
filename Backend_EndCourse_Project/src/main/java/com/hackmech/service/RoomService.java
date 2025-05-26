package com.hackmech.service;

import com.hackmech.dto.RoomDTO;
import com.hackmech.entity.Room;
import com.hackmech.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();

        return rooms.stream()
                .map(room -> new RoomDTO(
                        room.getId(),
                        room.getName(),
                        room.getLocation(),
                        room.getCapacity()
                ))
                .collect(Collectors.toList());
    }
}
