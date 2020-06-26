INSERT INTO desired_services (id, title, description) VALUES
(1, 'Full Service', 'Everything, I will be your slave'),
(2, 'Partial Service', 'Ill show up on big days, like cake day or dress day'),
(3, 'Day of Service', 'I talk to you twice before the day and then I coordinate');

INSERT INTO event_types (id, title, description) VALUES
(1, 'Wedding', 'Getting hitched'),
(2, 'Corporate', 'Celebrate that money');

INSERT INTO venues (id, title, state, city, street_address, postal_code, is_meal_provided, is_archived) VALUES
('9e2d7974-a127-11ea-bb37-0242ac130002', 'Hogwarts', 'UK', 'London', '1234 Hogsmead', '4321', FALSE, FALSE),
('9e2d7974-a127-11ea-bb37-0242ac130012', 'The Woods', 'OK', 'Tulsa', '4323 JustUnderTheBridge', '7594', TRUE, FALSE);

INSERT INTO coordinators (id, title, office_state, office_city, office_address, office_postal_code, contact_email, maximum_distance_to_client, level_one_default_bid, level_two_default_bid, level_three_default_bid, is_archived, username) VALUES
('9e2d7973-a127-11ea-bb37-0242ac130002', 'Best Weddings', 'TX', 'Dallas', '4731 Inchester RD', '74321', 'best_weddings@gmail.com', 30, 900, 500, 200, FALSE, 'bobsy234'),
('9e2d7975-a127-11ea-bb37-0242ac130002', 'Hitched', 'CA', 'Modesto', '6789 Martin RD', '74321', 'hitched@gmail.com', 1000, 4900, 4500, 4200, FALSE, 'merica'),
('9e2d7976-a127-11ea-bb37-0242ac130002', 'I Am Hungry', 'TX', 'Dallas', '4731 Martin Luther St.', '74321', 'please@gmail.com', 5, 9, 5, 2, FALSE, 'sooners');

INSERT INTO events (id, event_date, event_size, event_type_id, desired_service_id, additional_user_comments, venue_id, desired_state, desired_city, desired_postal_code, coordinator_id, is_archived) VALUES
('9e2d7974-a127-11ea-bb37-0242ac130003', NOW() + interval '110 days', 120, 1, 1,'all white', '9e2d7974-a127-11ea-bb37-0242ac130002', 'TX', 'Dallas', '75761', '9e2d7973-a127-11ea-bb37-0242ac130002', FALSE),
('9e2d7974-a127-11ea-bb37-0242ac130004', NOW() + interval '110 days', 120, 1, 1,'all white', '9e2d7974-a127-11ea-bb37-0242ac130012', 'TX', 'Dallas', '75761', '9e2d7975-a127-11ea-bb37-0242ac130002', FALSE),
('9e2d7974-a127-11ea-bb37-0242ac130005', NOW() + interval '110 days', 120, 1, 1,'all white', '9e2d7974-a127-11ea-bb37-0242ac130012', 'TX', 'Dallas', '75761', '9e2d7976-a127-11ea-bb37-0242ac130002', FALSE);

INSERT INTO users (id, name, contact_email, contact_phone_number, event_id, is_archived) VALUES
('8e2d7974-a127-11ea-bb37-0242ac130004', 'Austin', 'austin@gmail.com', '5555555555', '9e2d7974-a127-11ea-bb37-0242ac130003', FALSE),
('7e2d7974-a127-11ea-bb37-0242ac130004', 'Andrew', 'andrew@gmail.com', '6666666666', '9e2d7974-a127-11ea-bb37-0242ac130004', FALSE),
('6e2d7974-a127-11ea-bb37-0242ac130004', 'Adnan', 'adnan@gmail.com', '4444444444', '9e2d7974-a127-11ea-bb37-0242ac130005', FALSE);


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


INSERT INTO auth_user_roles (id, title) VALUES
(1, 'admin'),
(2, 'user'),
(3, 'coordinator');

INSERT INTO auth_users (id, username, password, auth_user_role_id) VALUES
('8e2d7974-a138-11ea-bb37-0242ac130004', 'kyleIsCool', '$2a$10$VAMuUkcmL.BQzacV4afFfu7Z6jQDGYVo4RpKdrbu7tZOg4CkSnbPO', 1),
('8e2d7974-a148-11ea-bb37-0242ac130004', 'chrisIsCool', '$2a$10$VAMuUkcmL.BQzacV4afFfu7Z6jQDGYVo4RpKdrbu7tZOg4CkSnbPO', 2),
('8e2d7974-a158-11ea-bb37-0242ac130004', 'bobIsCool', '$2a$10$VAMuUkcmL.BQzacV4afFfu7Z6jQDGYVo4RpKdrbu7tZOg4CkSnbPO', 3);
