package kz.alim.hotel.controllers;

import kz.alim.hotel.data.entities.*;
import kz.alim.hotel.data.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/manager")
public class ManagerControllerNew {

    public final SeasonRepository seasonRepository;
    public final RoomRepository roomRepository;
    public final RoomOfferRepository roomOfferRepository;
    public final RoomTypeRepository roomTypeRepository;
    public final RoomFeatureRepository roomFeatureRepository;
    public final HotelRepository hotelRepository;

    public ManagerControllerNew(SeasonRepository seasonRepository, RoomRepository roomRepository, RoomOfferRepository roomOfferRepository, RoomTypeRepository roomTypeRepository, RoomFeatureRepository roomFeatureRepository, HotelRepository hotelRepository) {
        this.seasonRepository = seasonRepository;
        this.roomRepository = roomRepository;
        this.roomOfferRepository = roomOfferRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.roomFeatureRepository = roomFeatureRepository;
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/season")
    public String AddSeasonGet(Model model) {
        model.addAttribute("seasons", seasonRepository.findAll());
        return "seasons";
    }

    @PostMapping("/season")
    public String AddSeasonPost(Model model, @RequestParam String name, @RequestParam String start, @RequestParam String end) {
        Season s = new Season(); // 2020-11-17
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        s.Name = name;
        s.Start = LocalDate.parse(start, formatter).atTime(0, 0);
        s.End = LocalDate.parse(end, formatter).atTime(0, 0);
        seasonRepository.save(s);
        model.addAttribute("seasons", seasonRepository.findAll());
        return "seasons";
    }

    @GetMapping("/roomType")
    public String RoomTypeGet(Model model) {
        model.addAttribute("types", roomTypeRepository.findAll());
        model.addAttribute("hotels", hotelRepository.findAll());
        model.addAttribute("features", roomFeatureRepository.findAll());
        return "roomsType";
    }

    @PostMapping("/roomType")
    public String RoomTypePost(Model model, @RequestParam String name, @RequestParam float size, @RequestParam int capacity,
                            @RequestParam long hotelId, @RequestParam Long[] featureId) {
        RoomType roomType = new RoomType();
        roomType.Name = name;
        roomType.Capacity = capacity;
        roomType.Size = size;
        roomType.Features = roomFeatureRepository.findAllById(Arrays.asList(featureId));
        roomType.Hotel = hotelRepository.findById(hotelId).orElseThrow();
        roomTypeRepository.save(roomType);
        model.addAttribute("types", roomTypeRepository.findAll());
        model.addAttribute("hotels", hotelRepository.findAll());
        model.addAttribute("features", roomFeatureRepository.findAll());
        return "roomsType";
    }

    @GetMapping("/features")
    public String FeaturesGet(Model model) {
        model.addAttribute("features", roomFeatureRepository.findAll());
        return "features";
    }

    @PostMapping("/features")
    public String FeaturesPost(Model model, @RequestParam String name) {
        RoomFeature f = new RoomFeature();
        f.Name = name;
        roomFeatureRepository.save(f);
        model.addAttribute("features", roomFeatureRepository.findAll());
        return "features";
    }

    @GetMapping("/rooms")
    public String RoomGet(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        model.addAttribute("hotelTypes", roomTypeRepository.findAll());
        model.addAttribute("hotels", hotelRepository.findAll());
        return "rooms";
    }

    @PostMapping("/rooms")
    public String RoomPost(Model model, @RequestParam String number, @RequestParam int floor,
                        @RequestParam long hotelId, @RequestParam long hotelTypeId) {
        Room r = new Room();
        r.Number = number;
        r.Floor = floor;
        r.Hotel = hotelRepository.findById(hotelId).orElseThrow();
        r.Type = roomTypeRepository.findById(hotelTypeId).orElseThrow();
        roomRepository.save(r);
        model.addAttribute("hotelTypes", roomTypeRepository.findAll());
        model.addAttribute("hotels", hotelRepository.findAll());
        return "rooms";
    }
}
