INSERT INTO desired_services (id, title, description) VALUES
(1, 'Full Service', 'Everything, I will be your slave'),
(2, 'Partial Service', 'Ill show up on big days, like cake day or dress day'),
(3, 'Day of Service', 'I talk to you twice before the day and then I coordinate');

INSERT INTO event_types (id, title, description) VALUES
(1, 'Wedding', 'Getting hitched'),
(2, 'Corporate', 'Celebrate that money');

INSERT INTO auth_user_roles (id, title) VALUES
(1, 'admin'),
(2, 'user'),
(3, 'coordinator');

INSERT INTO auth_users (id, username, password, auth_user_role_id) VALUES
('8e2d7974-a138-11ea-bb37-0242ac130004', 'admin', '$2a$10$VAMuUkcmL.BQzacV4afFfu7Z6jQDGYVo4RpKdrbu7tZOg4CkSnbPO', 1),
('8e2d7576-a148-11ea-bb37-0242ac130004', 'coordinator', '$2a$10$VAMuUkcmL.BQzacV4afFfu7Z6jQDGYVo4RpKdrbu7tZOg4CkSnbPO', 3),
('8e2d7376-a148-11ea-bb37-0242ac130004', 'coordinatorbob', '$2a$10$VAMuUkcmL.BQzacV4afFfu7Z6jQDGYVo4RpKdrbu7tZOg4CkSnbPO', 3),
('8e2d7476-a148-11ea-bb37-0242ac130004', 'coordinatorsam', '$2a$10$VAMuUkcmL.BQzacV4afFfu7Z6jQDGYVo4RpKdrbu7tZOg4CkSnbPO', 3),
('8e2d7974-a158-11ea-bb37-0242ac130004', 'user', '$2a$10$VAMuUkcmL.BQzacV4afFfu7Z6jQDGYVo4RpKdrbu7tZOg4CkSnbPO', 2),
('8e2d7974-a148-11ea-bb37-1242ac130004', 'userbob', '$2a$10$VAMuUkcmL.BQzacV4afFfu7Z6jQDGYVo4RpKdrbu7tZOg4CkSnbPO', 2),
('8e2d7974-a158-11ea-bb37-2342ac130004', 'usersam', '$2a$10$VAMuUkcmL.BQzacV4afFfu7Z6jQDGYVo4RpKdrbu7tZOg4CkSnbPO', 2);

INSERT INTO zip_codes (id, title) VALUES
('c6e17c26-9e40-4e19-8339-28772ef184d1', '75149'),
('c6e17c26-9e40-4e19-8339-28772ef184d2', '76036');

INSERT INTO coordinators (id, title, office_state, office_city, office_address, office_postal_code, contact_email, maximum_distance_to_client, level_one_default_bid, level_two_default_bid, level_three_default_bid, is_archived, auth_user_id) VALUES
('9e2d7973-a127-11ea-bb37-0242ac130002', 'Best Weddings', 'TX', 'Dallas', '4731 Inchester RD', '74321', 'best_weddings@gmail.com', 30, 900, 500, 200, FALSE, '8e2d7576-a148-11ea-bb37-0242ac130004'),
('9e2d7975-a127-11ea-bb37-0242ac130002', 'Hitched', 'CA', 'Modesto', '6789 Martin RD', '74321', 'hitched@gmail.com', 1000, 4900, 4500, 4200, FALSE, '8e2d7376-a148-11ea-bb37-0242ac130004'),
('9e2d7976-a127-11ea-bb37-0242ac130002', 'I Am Hungry', 'TX', 'Dallas', '4731 Martin Luther St.', '74321', 'please@gmail.com', 5, 9, 5, 2, FALSE, '8e2d7476-a148-11ea-bb37-0242ac130004');

INSERT INTO coordinators_zip_codes (id, coordinator_id, zip_code_id) VALUES
('9e3d7973-a127-11ea-bb37-0242bc130002', '9e2d7973-a127-11ea-bb37-0242ac130002', 'c6e17c26-9e40-4e19-8339-28772ef184d1'),
('9e2d7973-a127-11ea-bb37-0242ac142002', '9e2d7973-a127-11ea-bb37-0242ac130002', 'c6e17c26-9e40-4e19-8339-28772ef184d2'),
('9e2a6973-a127-11ea-bb37-0242ac130002', '9e2d7975-a127-11ea-bb37-0242ac130002', 'c6e17c26-9e40-4e19-8339-28772ef184d1'),
('9e2a3273-a127-11ea-bb37-0242ac130002', '9e2d7976-a127-11ea-bb37-0242ac130002', 'c6e17c26-9e40-4e19-8339-28772ef184d2');

INSERT INTO users (id, name, contact_email, contact_phone_number, is_archived, auth_user_id) VALUES
('8e2d7974-a127-11ea-bb37-0242ac130004', 'Austin', 'austin@gmail.com', '5555555555', FALSE, '8e2d7974-a158-11ea-bb37-0242ac130004'),
('7e2d7974-a127-11ea-bb37-0242ac130004', 'Andrew', 'andrew@gmail.com', '6666666666', FALSE, '8e2d7974-a148-11ea-bb37-1242ac130004'),
('6e2d7974-a127-11ea-bb37-0242ac130004', 'Adnan', 'adnan@gmail.com', '4444444444', FALSE, '8e2d7974-a158-11ea-bb37-2342ac130004');

INSERT INTO service_type VALUES
(1, 'floral'),
(2, 'dress'),
(3, 'party attire'),
(4, 'catering'),
(5, 'security'),
(6, 'bar'),
(7, 'photographer'),
(8, 'videographer'),
(9, 'musician'),
(10, 'cosmetician'),
(11, 'baby sitter'),
(12, 'other');

INSERT INTO event_size_type(id, title) VALUES
(1, '0-50'),
(2, '51-100'),
(3, '101-150');

INSERT INTO events (id, user_id, event_start_date, event_size, event_type_id, desired_service_id, additional_user_comments, desired_state, desired_city, desired_postal_code, coordinator_id, is_archived) VALUES
('9e2d7974-a127-11ea-bb37-0242ac130003', '8e2d7974-a127-11ea-bb37-0242ac130004', NOW() + interval '110 days', 1, 1, 1,'all white', 'TX', 'Dallas', '75761', '9e2d7973-a127-11ea-bb37-0242ac130002', FALSE),
('9e2d7974-a127-11ea-bb37-0242ac130004', '7e2d7974-a127-11ea-bb37-0242ac130004', NOW() + interval '110 days', 1, 1, 1,'all white', 'TX', 'Dallas', '75761', '9e2d7975-a127-11ea-bb37-0242ac130002', FALSE),
('9e2d7974-a127-11ea-bb37-0242ac130005', '6e2d7974-a127-11ea-bb37-0242ac130004', NOW() + interval '110 days', 1, 1, 1,'all white', 'TX', 'Dallas', '75761', '9e2d7976-a127-11ea-bb37-0242ac130002', FALSE);

INSERT INTO roles (id, title) VALUES
(1, 'bride'),
(2, 'groom'),
(3, 'maid of honor'),
(4, 'mother of the bride');

INSERT INTO bid_statuses (id, title) VALUES
(1, 'new'),
(2, 'pending'),
(3, 'accepted'),
(4, 'rejected');

INSERT INTO bids (id, bid_status_id, bid_amount, message_to_user, event_id, coordinator_id) VALUES
('8e2d7974-a128-11ea-bb37-0242ac130004', 1, 350, 'Pick me!!!! I am america and so can you.', '9e2d7974-a127-11ea-bb37-0242ac130003', '9e2d7973-a127-11ea-bb37-0242ac130002'),
('8e2d7974-a128-21ea-bb37-0242ac130004', 1, 400, 'Yahhhhhhhhhhhh', '9e2d7974-a127-11ea-bb37-0242ac130003', '9e2d7975-a127-11ea-bb37-0242ac130002'),
('8e2d7974-a128-31ea-bb37-0242ac130004', 1, 1000, 'Here is what I will give you.', '9e2d7974-a127-11ea-bb37-0242ac130003', '9e2d7976-a127-11ea-bb37-0242ac130002'),
('8e2d7974-a128-41ea-bb37-0242ac130004', 3, 350, 'I am america and so can you.', '9e2d7974-a127-11ea-bb37-0242ac130004', '9e2d7975-a127-11ea-bb37-0242ac130002'),
('8e2d7974-a128-51ea-bb37-0242ac130004', 4, 350, 'I am so hungry', '9e2d7974-a127-11ea-bb37-0242ac130004', '9e2d7976-a127-11ea-bb37-0242ac130002');
