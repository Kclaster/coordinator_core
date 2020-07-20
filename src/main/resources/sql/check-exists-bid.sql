SELECT 1 as hasRow
from bids b
WHERE b.event_id = :eventId
AND b.coordinator_id = :coordinatorId