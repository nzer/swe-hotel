package kz.alim.hotel.data;

import kz.alim.hotel.controllers.AuthController;
import kz.alim.hotel.data.entities.*;
import kz.alim.hotel.data.repositories.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class Seeder implements ApplicationRunner {
    public final RoomOfferRepository roomOfferRepository;
    public final SeasonRepository seasonRepository;
    public final RoomTypeRepository roomTypeRepository;
    public final AuthController authController;
    public final RoomRepository roomRepository;
    public final RoomFeatureRepository roomFeatureRepository;
    public final HotelRepository hotelRepository;
    public final RoomServiceRepository roomServiceRepository;

    public Seeder(RoomOfferRepository roomOfferRepository, SeasonRepository seasonRepository, RoomTypeRepository roomTypeRepository, AuthController authController, RoomRepository roomRepository, RoomFeatureRepository roomFeatureRepository, HotelRepository hotelRepository, RoomServiceRepository roomServiceRepository) {
        this.roomOfferRepository = roomOfferRepository;
        this.seasonRepository = seasonRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.authController = authController;
        this.roomRepository = roomRepository;
        this.roomFeatureRepository = roomFeatureRepository;
        this.hotelRepository = hotelRepository;
        this.roomServiceRepository = roomServiceRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RoomFeature rf1 = new RoomFeature();
        rf1.Name = "Flat TV";
        RoomFeature rf2 = new RoomFeature();
        rf2.Name = "Freezer";
        RoomFeature rf3 = new RoomFeature();
        rf3.Name = "Large bathroom";
        RoomFeature rf4 = new RoomFeature();
        rf4.Name = "Breakfast";
        RoomFeature rf5 = new RoomFeature();
        rf5.Name = "Hairdryer";
        roomFeatureRepository.saveAll(Arrays.asList(rf1, rf2, rf3, rf4, rf5));
        Season season = new Season();
        season.Start = LocalDateTime.now().minus(1, ChronoUnit.YEARS);
        season.End = LocalDateTime.now().plus(1, ChronoUnit.YEARS);
        season.Name = "Yearly season";
        seasonRepository.save(season);

        String[] phones1 = {"+7 (727) 264‒02‒16", "+7 (727) 385‒10‒00"};
        genHotel("VIP Hotel", "Dostyk 33",  phones1);
        String[] phones2 = {"+7 (727) 262‒42‒86", "+7 (707) 869‒02‒53"};
        genHotel("Silver Hotel", "Satpaeva 23", phones2);

        // Seed users

        AuthController.RegisterGuestDto registerGuestDto = new AuthController.RegisterGuestDto();
        registerGuestDto.Name = "Tima";
        registerGuestDto.Login = "Tima";
        registerGuestDto.Password = "123";
        registerGuestDto.Address = "Dostyk 66";
        registerGuestDto.HomePhone = "213421";
        registerGuestDto.MobilePhone = "213421";
        authController.Register(registerGuestDto);
        authController.RegisterManager("Boss", "123");
    }

    private void genHotel(String name, String address, String[] phones) {
        Hotel hotel1 = new Hotel();
        hotel1.Address = address;
        hotel1.Name = name;
        hotel1.Phones = Arrays.asList(phones);
        hotelRepository.save(hotel1);

        HotelService service1 = new HotelService();
        service1.Name = "Dry clean";
        service1.Hotel = hotel1;
        service1.Price = 3000;
        HotelService service2 = new HotelService();
        service2.Name = "Childcare";
        service2.Hotel = hotel1;
        service2.Price = 6000;
        HotelService service3 = new HotelService();
        service3.Name = "SPA";
        service3.Hotel = hotel1;
        service3.Price = 30000;
        HotelService service4 = new HotelService();
        service4.Name = "Airport transfer";
        service4.Hotel = hotel1;
        service4.Price = 5000;
        roomServiceRepository.saveAll(Arrays.asList(service1, service2, service3, service4));

        Season season = seasonRepository.findById(1L).orElseThrow();

        // Generate rooms

        RoomType rt1 = genRoomType();
        rt1.Hotel = hotel1;
        roomTypeRepository.save(rt1);
        genRoomOffer(rt1, season);
        genRoomOffer(rt1, season);
        genRoomOffer(rt1, season);

        RoomType rt2 = genRoomType();
        rt2.Hotel = hotel1;
        roomTypeRepository.save(rt2);
        genRoomOffer(rt2, season);
        genRoomOffer(rt2, season);
        genRoomOffer(rt2, season);

        for (RoomType roomType : roomTypeRepository.findAll()) {
            for (int i = 1; i < 3; i++) {
                Room room = new Room();
                room.Type = roomType;
                room.Hotel = hotel1;
                room.Floor = 1;
                room.Number = "10" + roomType.Id + i;
                roomRepository.save(room);
            }
        }
    }

    private void genRoomOffer(RoomType rt1, Season season) {
        Random rand = new Random();
        RoomOffer roomOffer = new RoomOffer();
        roomOffer.Days = new ArrayList<>();
        roomOffer.Days.add(RoomOffer.DayOfWeek.Monday);
        roomOffer.Days.add(RoomOffer.DayOfWeek.Tuesday);
        roomOffer.Days.add(RoomOffer.DayOfWeek.Wednesday);
        roomOffer.Days.add(RoomOffer.DayOfWeek.Thursday);
        roomOffer.Days.add(RoomOffer.DayOfWeek.Friday);
        roomOffer.Days.add(RoomOffer.DayOfWeek.Saturday);
        roomOffer.Days.add(RoomOffer.DayOfWeek.Sunday);
        roomOffer.PricePerDay = rand.nextInt(45000) + 10000;
        roomOffer.Season = season;
        roomOffer.RoomType = rt1;
        roomOfferRepository.save(roomOffer);
    }

    private RoomType genRoomType() {
        Random rand = new Random();
        RoomType roomType = new RoomType();
        roomType.Size = rand.nextInt(40) + 10;
        roomType.Capacity = rand.nextInt(4) + 1;
        roomType.Name = "Room Type " + (rand.nextInt(7) + 1);
        List<RoomFeature> features = roomFeatureRepository.findAll();
        roomType.Features = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int randomIndex = rand.nextInt(features.size());
            RoomFeature randomElement = features.get(randomIndex);
            roomType.Features.add(randomElement);
            features.remove(randomIndex);
        }
        return roomType;
    }
}
