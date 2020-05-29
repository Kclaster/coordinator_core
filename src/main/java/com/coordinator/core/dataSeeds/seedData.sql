INSERT INTO desired_services (id, title, description) VALUES
(1, 'Full Service', 'Everything, I will be your slave'),
(2, 'Partial Service', 'Ill show up on big days, like cake day or dress day'),
(3, 'Day of Service', 'I talk to you twice before the day and then I coordinate');

INSERT INTO event_types (id, title, description) VALUES
(1, 'Wedding', 'Getting hitched'),
(2, 'Corporate', 'Celebrate that money');

INSERT INTO venues (id, title, state, city, street_address, postal_code, is_meal_provided) VALUES
('9e2d7974-a127-11ea-bb37-0242ac130002', 'Hogwarts', 'UK', 'London', '1234 Hogsmead', '4321', FALSE),
('9e2d7974-a127-11ea-bb37-0242ac130012', 'The Woods', 'OK', 'Tulsa', '4323 JustUnderTheBridge', '7594', TRUE);

INSERT INTO events (id, event_date, event_size, event_type_id, desired_service_id, additional_user_comments, venue_id, desired_state, desired_city, desired_postal_code) VALUES
('9e2d7974-a127-11ea-bb37-0242ac130003', NOW() + interval '110 days', 120, 1, 1,'all white', '9e2d7974-a127-11ea-bb37-0242ac130002', 'TX', 'Dallas', '75761'),
('9e2d7974-a127-11ea-bb37-0242ac130004', NOW() + interval '110 days', 120, 1, 1,'all white', '9e2d7974-a127-11ea-bb37-0242ac130012', 'TX', 'Dallas', '75761'),
('9e2d7974-a127-11ea-bb37-0242ac130005', NOW() + interval '110 days', 120, 1, 1,'all white', '9e2d7974-a127-11ea-bb37-0242ac130012', 'TX', 'Dallas', '75761');

INSERT INTO users (id, last_name, first_name, role_id, contact_email, contact_phone_number, event_id) VALUES
('8e2d7974-a127-11ea-bb37-0242ac130004', 'Chappell', 'Austin', 1, 'austin@gmail.com', '5555555555', '9e2d7974-a127-11ea-bb37-0242ac130003'),
('7e2d7974-a127-11ea-bb37-0242ac130004', 'SomeCrazyAssName', 'Andrew', 2, 'andrew@gmail.com', '6666666666', '9e2d7974-a127-11ea-bb37-0242ac130004'),
('6e2d7974-a127-11ea-bb37-0242ac130004', 'Khan', 'Adnan', 4, 'adnan@gmail.com', '4444444444', '9e2d7974-a127-11ea-bb37-0242ac130005');


INSERT INTO roles (id, title) VALUES
(1, 'bride'),
(2, 'groom'),
(3, 'maid of honor'),
(4, 'mother of the bride');
