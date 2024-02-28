package com.aeswibon.arike.token.db.query

object TokenQueries {
  const val TOKEN_FETCH = """
        select t from Token t inner join User u
        on t.user.uuid = u.uuid        
        where u.uuid = :uuid and (t.expired = false or t.revoked = false)
    """
}
