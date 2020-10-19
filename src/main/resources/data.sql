INSERT INTO hotel.hotel (id, address, name)
VALUES (1, 'Dostyk 72', 'Kazakhstan');

INSERT INTO hotel.hotel (id, address, name)
VALUES (2, 'Dostyk 112', 'Koktem');

INSERT INTO hotel.hotel_phones (hotel_id, phones)
VALUES (1, '+77278132111');

INSERT INTO hotel.hotel_phones (hotel_id, phones)
VALUES (1, '+77278132222');

INSERT INTO hotel.hotel_phones (hotel_id, phones)
VALUES (2, '+77273132111');

INSERT INTO hotel.hotel_phones (hotel_id, phones)
VALUES (2, '+77273142111');

INSERT INTO hotel.hotel_phones (hotel_id, phones)
VALUES (2, '+77273142333');

INSERT INTO hotel.guest (id, address, home_phone, identity_type, mobile_phone, name, type_id)
VALUES (1, 'Abay 44', '+77272336611', 'Passport', '+77779995566', 'Dima', null);

INSERT INTO hotel.room_type (capacity, name, size, hotel_id)
VALUES (2, 'Twin standard', 15, 1);

INSERT INTO hotel.room_type (capacity, name, size, hotel_id)
VALUES (2, 'Twin luxury', 25, 1);

INSERT INTO hotel.room_type (capacity, name, size, hotel_id)
VALUES (2, 'Twin standard', 16, 2);

INSERT INTO hotel.room_type (capacity, name, size, hotel_id)
VALUES (2, 'Double standard', 20, 2);

INSERT INTO hotel.room_type (capacity, name, size, hotel_id)
VALUES (3, 'Family standard', 23, 2);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (1, 1, '101', 1, 1);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (2, 1, '102', 1, 1);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (3, 1, '103', 1, 1);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (4, 1, '104', 1, 1);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (5, 1, '105', 1, 1);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (6, 2, '201', 1, 2);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (7, 2, '202', 1, 2);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (8, 2, '203', 1, 2);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (9, 1, '105', 2, 3);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (10, 1, '106', 2, 3);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (11, 1, '107', 2, 3);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (12, 1, '108', 2, 3);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (13, 2, '201', 2, 4);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (14, 2, '202', 2, 4);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (15, 2, '203', 2, 5);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (16, 2, '204', 2, 5);

INSERT INTO hotel.room_feature (id, name)
VALUES (1, 'TV');

INSERT INTO hotel.room_feature (id, name)
VALUES (2, 'Mountain View');

INSERT INTO hotel.room_feature (id, name)
VALUES (3, 'Minibar');

INSERT INTO hotel.room_type_features (room_type_id, features_id)
VALUES (2, 1);

INSERT INTO hotel.room_type_features (room_type_id, features_id)
VALUES (2, 2);

INSERT INTO hotel.room_type_features (room_type_id, features_id)
VALUES (2, 3);

INSERT INTO hotel.room_type_features (room_type_id, features_id)
VALUES (3, 1);

INSERT INTO hotel.room_type_features (room_type_id, features_id)
VALUES (4, 1);

INSERT INTO hotel.room_type_features (room_type_id, features_id)
VALUES (5, 1);

