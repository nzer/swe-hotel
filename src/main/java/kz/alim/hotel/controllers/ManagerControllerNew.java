package kz.alim.hotel.controllers;

import kz.alim.hotel.data.entities.*;
import kz.alim.hotel.data.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("/main")
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
        s.Start = LocalDate.parse(start, formatter).atTime(0,0);
        s.End = LocalDate.parse(end, formatter).atTime(0,0);
        seasonRepository.save(s);
        model.addAttribute("seasons", seasonRepository.findAll());
        return "seasons";
    }

    @GetMapping("/room")
    public String RoomsGet(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        return "rooms";
    }

    @PostMapping("/rooms/add")
    public Room AddRoom(@RequestBody AddRoomDto request) {
        Room r = new Room();
        r.Floor = request.Floor;
        r.Number = request.Number;
        r.Hotel = hotelRepository.findById(request.HotelId).orElseThrow();
        r.Type = roomTypeRepository.findById(request.RoomTypeId).orElseThrow();
        roomRepository.save(r);
        return r;
    }

    @PostMapping("/rooms_types/add")
    public RoomType AddRoomType(@RequestBody AddRoomTypeDto request) {
        RoomType r = new RoomType();
        r.Name = request.Name;
        r.Capacity = request.Capacity;
        r.Size = request.Size;
        r.Hotel = hotelRepository.findById(request.HotelId).orElseThrow();
        r.Features = roomFeatureRepository.findAllById(request.FeaturesId);
        return r;
    }

    @PostMapping("/rooms_types/list")
    public Iterable<RoomType> AllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    @PostMapping("/rooms_features/add")
    public RoomFeature AddRoomFeature(@RequestBody AddRoomFeatureDto request) {
        RoomFeature r = new RoomFeature();
        r.Name = request.Name;
        roomFeatureRepository.save(r);
        return r;
    }

    @PostMapping("/rooms_features/list")
    public Iterable<RoomFeature> AllRoomFeatures() {
        return roomFeatureRepository.findAll();
    }

    @PostMapping("/hotels/list")
    public Iterable<Hotel> AllHotels() {
        return hotelRepository.findAll();
    }

    public static class AddSeasonDto {
        public String Name;
        public LocalDateTime Start;
        public LocalDateTime End;
    }

    public static class AddRoomDto {
        public String Number;
        public int Floor;
        public long HotelId;
        public long RoomTypeId;
    }

    public static class AddRoomTypeDto {
        public String Name;
        public float Size;
        public int Capacity;
        public long HotelId;
        public List<Long> FeaturesId;
    }

    public static class AddRoomFeatureDto {
        public String Name;
    }
}
