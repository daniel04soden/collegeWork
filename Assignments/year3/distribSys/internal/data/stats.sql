
CREATE TABLE IF NOT EXISTS users (
    userID INTEGER PRIMARY KEY,  
    noGamesPlayed INT,
    averageScore REAL,
    highscore INT
);

CREATE TABLE IF NOT EXISTS gameStatistics (
    gameID INTEGER PRIMARY KEY,  
    userID INT NOT NULL,
    totalScore REAL,
    letters VARCHAR(10),
    FOREIGN KEY (userID)
    REFERENCES users(userID)
);

INSERT INTO users (noGamesPlayed, averageScore, highscore) VALUES
(5, 45.2, 78),
(12, 81.95, 115);


INSERT INTO gameStatistics (gameID, userID, totalScore, letters) VALUES
(1, 1, 55.0, 'irogedn'),
(2, 2, 23.5, 'tpfnoie');
