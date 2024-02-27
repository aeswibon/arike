create table if not exists tokens
(
    id         SERIAL PRIMARY KEY,
    token      VARCHAR(255) UNIQUE NOT NULL,
    token_type VARCHAR(50) NOT NULL,
    revoked BOOLEAN NOT NULL,
    expired BOOLEAN NOT NULL,
    user_uuid UUID REFERENCES users(uuid) ON DELETE CASCADE
)
