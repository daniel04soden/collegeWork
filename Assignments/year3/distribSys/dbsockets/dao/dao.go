package dao

import (
	"database/sql"
	"log"
	"strings"

	_ "github.com/mattn/go-sqlite3"
)

/*
CREATE TABLE IF NOT EXISTS gameStatistics (
gameID INTEGER PRIMARY KEY,
totalScore REAL,
averageScore REAL,
guesses VARCHAR(255), // Removed trailing comma here
letters VARCHAR(10)
);
*/

const dbPath string = "./db/stats.db"

type gameStatistics struct {
	ID      int
	Total   float64
	Avg     float64
	Guesses string 
	Letters string
}

/*
INSERT INTO gameStatistics (gameID, totalScore, averageScore, guesses, letters) VALUES
(1, 30.0, 5.0, 'iron,dine,done,gird,goer,rode,ring,rend,ending,region,rodeo,gender', 'irogedn');
*/


func ListGames() ([]gameStatistics, error) {
	var res []gameStatistics

	db, err := sql.Open("sqlite3", dbPath)
	if err != nil {
		return res, err
	}
	defer db.Close()

	rows, err := db.Query("SELECT gameID, totalScore, averageScore, guesses, letters FROM gameStatistics")
	if err != nil {
		log.Printf("Database query error: %v", err)
		return nil, err
	}
	defer rows.Close()
	for rows.Next() {
		var stats gameStatistics

		err = rows.Scan(
			&stats.ID,
			&stats.Total,
			&stats.Avg,
			&stats.Guesses,
			&stats.Letters,
			)

		if err != nil {
			log.Printf("Database error while scanning row: %v", err)
			return nil, err
		}

		res = append(res, stats)
	}

	if err = rows.Err(); err != nil {
		log.Printf("Error after iterating rows: %v", err)
		return nil, err
	}

	return res, nil
}


func getGame(id int) (gameStatistics, error) {
	db, err := sql.Open("sqlite3", dbPath)
	if err != nil {
		return gameStatistics{}, err
	}
	defer db.Close()

	var stats gameStatistics

	row := db.QueryRow(
		"SELECT gameID, totalScore, averageScore, guesses, letters FROM gameStatistics WHERE gameID = ?",
		id,
		)

	err = row.Scan(
		&stats.ID, 
		&stats.Total, 
		&stats.Avg, 
		&stats.Guesses, 
		&stats.Letters,
		)

	if err != nil {
		if err == sql.ErrNoRows {
			log.Printf("No game found with ID: %d", id)
			return gameStatistics{}, err
		}
		log.Printf("Database error while scanning row: %v", err)
		return gameStatistics{}, err
	}

	return stats, nil
}

func GetAverage(id int) (float64,error){
	game,err:=getGame(id)
	if err!=nil{
		log.Printf("No game found with ID: %d", id)
		return 0,err
	}
	return game.Avg,nil
}

func GetTotal(id int) (float64,error){
	game,err:=getGame(id)
	if err!=nil{
		log.Printf("No game found with ID: %d", id)
		return 0,err
	}
	return game.Total,nil
}

func GetGuesses(id int) ([]string,error){
	game,err:=getGame(id)
	if err!=nil{
		log.Printf("No game found with ID: %d", id)
		return nil,err
	}
	var res []string = strings.Split(game.Guesses,",")
	return res,nil 
}

func GetLetters(id int) (string,error){
	game,err:=getGame(id)
	if err!=nil{
		log.Printf("No game found with ID: %d", id)
		return "",err
	}
	return game.Letters,nil
}
