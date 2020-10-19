package kz.alim.hotel.controllers;

import kz.alim.hotel.data.HotelRepository;
import kz.alim.hotel.data.entities.Hotel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping
    public Iterable<Hotel> GetAllHotels(){
        return hotelRepository.findAll();
    }
}
