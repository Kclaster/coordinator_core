UPDATE coordinators SET
    title = :title
    , office_state = :officeState
    , office_address = :officeAddress
    , office_postal_code = :officePostalCode
    , office_city = :officeCity
    , contact_email = :contactEmail
    , maximum_distance_to_client = :maximumDistanceToClient
    , level_one_default_bid = :levelOneDefaultBid
    , level_two_default_bid = :levelTwoDefaultBid
    , level_three_default_bid = :levelThreeDefaultBid
WHERE id = :coordinatorId