package kz.alim.hotel.controllers;

import kz.alim.hotel.data.HotelRepository;
import kz.alim.hotel.data.entities.Hotel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    private final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping
    public Iterable<Hotel> GetAllHotels(){
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    Hotel GetHotelById(@PathVariable long id) {
        return hotelRepository.findById(id).orElseThrow();
    }
}
