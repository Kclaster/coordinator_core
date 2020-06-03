SELECT B.id as id
    , B.bid_status_id as bidStatusId
    , B.bid_amount as bidAmount
    , B.message_to_user as messageToUser
    , B.event_id as eventId
    , B.coordinator_id as coordinatorId
    , e.event_date as eventDate
    , e.event_size as eventSize
    , e.event_type_id as eventTypeId
    , e.desired_service_id as desiredServiceId
    , e.additional_user_comments as additionalUserComments
    , e.venue_id as venueId
    , e.desired_state as desiredState
    , e.desired_city as desiredCity
    , e.desired_postal_code as desiredPostalCode
    , e.coordinator_id as coordinatorId
FROM bids B
JOIN events E
    ON E.id = B.event_id
WHERE B.coordinator_id = :coordinator_id

