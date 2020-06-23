INSERT INTO events (
    id
    , event_date
    , event_size
    , desired_service_id
    , additional_user_comments
    , venue_id
    , desired_state
    , desired_city
    , desired_postal_code
    , is_archived
    )
    VALUES (
    :eventId
    , :eventDate
    , :eventSize
    , :desiredServiceId
    , :additionalUserComments
    , :venueId
    , :desiredState
    , :desiredCity
    , :desiredPostalCode
    , 'false'
)