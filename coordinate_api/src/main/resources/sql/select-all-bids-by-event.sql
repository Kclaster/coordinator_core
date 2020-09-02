SELECT id
    , bid_status_id as bidStatusId
    , bid_amount as bidAmount
    , message_to_user as messageToUser
    , event_id as eventId
    , coordinator_id as coordinatorId
FROM bids
WHERE event_id = :eventId
