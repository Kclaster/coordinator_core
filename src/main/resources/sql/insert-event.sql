INSERT INTO events (
    id
    , event_start_date
    , event_end_date
    , event_size
    , event_type_id
    , desired_service_id
    , additional_user_comments
    , desired_state
    , desired_city
    , desired_postal_code
    , venue_id
    , is_archived
    )
    VALUES (
    :eventId
    , :eventStartDate
    , :eventEndDate
    , :eventSize
    , :eventTypeId
    , :desiredServiceId
    , :additionalUserComments
    , :desiredState
    , :desiredCity
    , :desiredPostalCode
    , :venueId
    , 'false'
)