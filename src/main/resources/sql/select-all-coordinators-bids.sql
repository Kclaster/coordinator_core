SELECT B.id as id
    , B.bid_status_id as bidStatusId
    , B.bid_amount as bidAmount
    , B.message_to_user as messageToUser
    , B.event_id as eventId
    , B.coordinator_id as coordinatorId
from bids B
WHERE B.coordinator_id = :coordinator_id

