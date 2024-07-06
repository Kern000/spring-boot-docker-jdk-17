
CREATE TABLE IF NOT EXISTS Practice (
    id INT NOT NULL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    time_started timestamp NOT NULL,
    time_ended timestamp NOT NULL,
    topics_covered VARCHAR(255) NOT NULL,
    coding_language VARCHAR(30) NOT NULL,
    version INT
);