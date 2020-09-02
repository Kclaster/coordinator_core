INSERT INTO bids (
    id
    , bid_status_id
    , message_to_user
    , event_id
    , coordinator_id
    , bid_amount
) VALUES (
    :bidId,
    :bidStatusId,
    :messageToUser,
    :eventId,
    :coordinatorId,
    :bidAmount
)