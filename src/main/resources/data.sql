INSERT INTO hotel.hotel (id, address, name)
VALUES (1, 'Dostyk 72', 'Kazakhstan');

INSERT INTO hotel.hotel_phones (hotel_id, phones)
VALUES (1, '+77278132111');

INSERT INTO hotel.hotel_phones (hotel_id, phones)
VALUES (1, '+77278132222');

INSERT INTO hotel.account (id, login, password, role)
VALUES ('1', 'tester', '1', 0);

INSERT INTO hotel.account (id, login, password, role)
VALUES ('2', 'clerk', '1', 1);

INSERT INTO hotel.account (id, login, password, role)
VALUES ('3', 'manager', '1', 2);

INSERT INTO hotel.guest (id, address, home_phone, identity_type, mobile_phone, name, type_id)
VALUES (1, 'Abay 44', '+77272336611', 'Passport', '+77779995566', 'Dima', null);

INSERT INTO hotel.room_type (capacity, name, size, hotel_id)
VALUES (2, 'Twin standard', 15, 1);

INSERT INTO hotel.room_type (capacity, name, size, hotel_id)
VALUES (2, 'Twin luxury', 25, 1);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (1, 1, '101', 1, 1);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (2, 1, '102', 1, 1);

INSERT INTO hotel.room (id, floor, number, hotel_id, type_id)
VALUES (3, 1, '103', 1, 1);

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