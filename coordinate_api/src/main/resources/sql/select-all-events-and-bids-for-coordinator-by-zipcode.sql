SELECT e.id as eventId
	, e.event_end_date as eventEndDate
	, e.event_start_date as eventStartDate
	, e.event_size as eventSize
	, e.user_id as userId
	, e.event_type_id as eventTypeId
	, e.desired_service_id as desiredServiceId
	, e.additional_user_comments as additionalUserComments
	, e.venue_id as venueId
	, e.desired_state as desiredState
	, e.desired_city as desiredCity
	, e.desired_postal_code as desiredPostalCode
	, e.coordinator_id as coordinatorId
	, e.is_archived as isArchived
	, b.id as bidId
	, b.bid_status_id as bidStatusId
	, b.bid_amount as bidAmount
	, b.message_to_user as messageToUser 
	FROM events e
	LEFT JOIN bids b ON e.coordinator_id = b.coordinator_id
Where e.desired_postal_code IN (
	SELECT zc.title FROM coordinators_zip_codes cz
	JOIN zip_codes zc ON cz.zip_code_id = zc.id
	WHERE cz.coordinator_id = :coordinatorId
)



