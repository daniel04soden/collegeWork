CREATE TABLE IF NOT EXISTS gameStatistics (
    gameID INTEGER PRIMARY KEY,
    totalScore REAL,
    averageScore REAL,
    guesses VARCHAR(255),
    letters VARCHAR(10)
);
---
INSERT INTO gameStatistics (gameID, totalScore, averageScore, guesses, letters) VALUES
(1, 30.0, 5.0, 'iron,dine,done,gird,goer,rode,ring,rend,ending,region,rodeo,gender', 'irogedn');

INSERT INTO gameStatistics (gameID, totalScore, averageScore, guesses, letters) VALUES
(2, 25.0, 5.0, 'toil,fine,pent,point,note', 'tpfnoie');
