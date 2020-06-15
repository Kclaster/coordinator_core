SELECT AU.username as username
    , AU.password as password
    , AU.auth_user_role_id as authUserRoleId
    , AU.is_expired as isExpired
    , AU.is_locked as isLocked
    , AU.is_credentials_expired as isCredentialsExpired
    , AU.is_enabled as isEnabled
 FROM auth_users AU
WHERE AU.username = ?