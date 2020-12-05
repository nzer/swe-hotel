package kz.alim.hotel.data;

import kz.alim.hotel.controllers.AuthController;
import kz.alim.hotel.data.entities.*;
import kz.alim.hotel.data.repositories.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
    public final GuestRepository guestRepository;
    public final GuestTypeRepository guestTypeRepository;
    public final RoomOfferDiscountRepository roomOfferDiscountRepository;
    public final ReservationRepository reservationRepository;
    public final HotelServiceRepository hotelServiceRepository;
    public final BillRepository billRepository;

    public Seeder(RoomOfferRepository roomOfferRepository, SeasonRepository seasonRepository, RoomTypeRepository roomTypeRepository, AuthController authController, RoomRepository roomRepository, RoomFeatureRepository roomFeatureRepository, HotelRepository hotelRepository, RoomServiceRepository roomServiceRepository, GuestRepository guestRepository, GuestTypeRepository guestTypeRepository, RoomOfferDiscountRepository roomOfferDiscountRepository, ReservationRepository reservationRepository, HotelServiceRepository hotelServiceRepository, BillRepository billRepository) {
        this.roomOfferRepository = roomOfferRepository;
        this.seasonRepository = seasonRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.authController = authController;
        this.roomRepository = roomRepository;
        this.roomFeatureRepository = roomFeatureRepository;
        this.hotelRepository = hotelRepository;
        this.roomServiceRepository = roomServiceRepository;
        this.guestRepository = guestRepository;
        this.guestTypeRepository = guestTypeRepository;
        this.roomOfferDiscountRepository = roomOfferDiscountRepository;
        this.reservationRepository = reservationRepository;
        this.hotelServiceRepository = hotelServiceRepository;
        this.billRepository = billRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> roomFeatures = Arrays.asList("Flat TV", "Freezer", "Large bathroom", "Breakfast", "Hairdryer", "Mountain view");
        for (String name : roomFeatures) {
            RoomFeature rf = new RoomFeature();
            rf.Name = name;
            roomFeatureRepository.save(rf);
        }

        List<String> guestTypes = Arrays.asList("Bronze", "Silver", "Gold", "Platinum");
        for (String guestType : guestTypes) {
            GuestType gt = new GuestType();
            gt.Name = guestType;
            guestTypeRepository.save(gt);
            genDiscount(gt);
        }

        Season season = new Season();
        season.Start = LocalDateTime.now().minus(1, ChronoUnit.YEARS);
        season.End = LocalDateTime.now().plus(1, ChronoUnit.YEARS);
        season.Name = "Year round season";
        seasonRepository.save(season);

        String[] phones1 = {"+7 (727) 264‒02‒16", "+7 (727) 385‒10‒00"};
        genHotel("Resort Hotel", "Dostyk 33",  phones1);
        String[] phones2 = {"+7 (727) 262‒42‒86", "+7 (707) 869‒02‒53"};
        genHotel("Silver Hotel", "Satpaeva 23", phones2);

        genUsers();

        Reservation reservation = new Reservation();
        reservation.RoomOffer = roomOfferRepository.findById(1L).orElseThrow();
        reservation.Room = roomRepository.findById(1L).orElseThrow();
        reservation.Occupants = Collections.singletonList(guestRepository.findByLogin("Mona"));
        reservation.Start = LocalDateTime.now();
        reservation.End = LocalDateTime.now().plusDays(5);
        reservation.PayingGuest = guestRepository.findByLogin("Mona");
        reservationRepository.save(reservation);

        Bill bill = new Bill();
        bill.Guest = guestRepository.findByLogin("Mona");
        bill.Reservations = Collections.singletonList(reservationRepository.findById(1L).orElseThrow());
        bill.Services = hotelServiceRepository.findAllById(Arrays.asList(1L,2L));
        bill.Hotel = hotelRepository.findById(1L).orElseThrow();
        bill.PaidOn = LocalDateTime.now().plusDays(5);
        billRepository.save(bill);

        Reservation reservation2 = new Reservation();
        reservation2.RoomOffer = roomOfferRepository.findById(2L).orElseThrow();
        reservation2.Room = roomRepository.findById(2L).orElseThrow();
        reservation2.Occupants = Collections.singletonList(guestRepository.findByLogin("Mona"));
        reservation2.Start = LocalDateTime.now().minusDays(15);
        reservation2.End = LocalDateTime.now().minusDays(12);
        reservation2.PayingGuest = guestRepository.findByLogin("Mona");
        reservationRepository.save(reservation2);

        Bill bill2 = new Bill();
        bill2.Guest = guestRepository.findByLogin("Mona");
        bill2.Reservations = Collections.singletonList(reservationRepository.findById(2L).orElseThrow());
        bill2.Services = hotelServiceRepository.findAllById(Arrays.asList(2L,3L));
        bill2.Hotel = hotelRepository.findById(1L).orElseThrow();
        bill2.PaidOn = LocalDateTime.now().minusDays(12);
        billRepository.save(bill2);
    }

    private void genUsers() {
        AuthController.RegisterGuestDto registerGuestDto = new AuthController.RegisterGuestDto();
        registerGuestDto.Name = "Mona";
        registerGuestDto.Login = "Mona";
        registerGuestDto.Password = "123";
        registerGuestDto.Address = "Dostyk 66";
        registerGuestDto.HomePhone = "213421";
        registerGuestDto.MobilePhone = "213421";
        authController.Register(registerGuestDto);
        Guest mona = guestRepository.findByLogin("Mona");
        mona.GuestType = guestTypeRepository.findById(3L).orElseThrow();
        guestRepository.save(mona);
        AuthController.RegisterGuestDto registerGuestDto2 = new AuthController.RegisterGuestDto();
        registerGuestDto2.Name = "Jon Smith";
        registerGuestDto2.Login = "Smith";
        registerGuestDto2.Password = "123";
        registerGuestDto2.Address = "Nur-sultan 23";
        registerGuestDto2.HomePhone = "564134";
        registerGuestDto2.MobilePhone = "456546";
        authController.Register(registerGuestDto2);
        authController.RegisterEmployee("Alim", "123", Account.AccountRole.MANAGER);
        authController.RegisterEmployee("Magjan", "123", Account.AccountRole.CLERK);
        authController.RegisterEmployee("Sabyr", "123", Account.AccountRole.CLEANER);
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

    static int discountCounter = 0;
    private RoomOfferDiscount genDiscount(GuestType offeredTo) {
        Random rand = new Random();
        RoomOfferDiscount discount = new RoomOfferDiscount();
        discount.Name = "Discount Type " + ++discountCounter;
        discount.Coefficient = rand.nextFloat() * (0.9f - 0.75f) + 0.75f;
        discount.OfferedTo = offeredTo;
        roomOfferDiscountRepository.save(discount);
        return discount;
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
