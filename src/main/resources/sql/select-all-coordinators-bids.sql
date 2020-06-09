SELECT b.id as id
    , b.bid_status_id as bidStatusId
    , b.bid_amount as bidAmount
    , b.message_to_user as messageToUser
    , b.event_id as eventId
    , b.coordinator_id as coordinatorId
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
    , e.is_archived as isArchived
FROM bids b
JOIN events e
    ON e.id = b.event_id
WHERE b.coordinator_id = :coordinator_id

