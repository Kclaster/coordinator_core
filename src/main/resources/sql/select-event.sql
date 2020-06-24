SELECT e.id as id
    , event_date as eventDate
    , event_size as eventSize
    , event_type_id as eventTypeId
    , desired_service_id as desiredServiceId
    , additional_user_comments as additionalUserComments
    , venue_id as venueId
    , desired_state as desiredState
    , desired_city as desiredCity
    , desired_postal_code as desiredPostalCode
    , coordinator_id as coordinatorId
FROM events e
    JOIN users u
    ON u.event_id = e.id
WHERE u.id = :userId