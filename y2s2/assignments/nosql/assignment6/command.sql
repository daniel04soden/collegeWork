-- Q 1
MATCH (ClintE:Person {name: 'Clint Eastwood'})  // create clint eastwood variable for fixing usage
CREATE (TheBridges:Movie {
  title: 'The Bridges of Madison County',
  released: 1995,
  tagline: 'This kinda certainty comes but just once in a lifetime.'
})
CREATE (MerylStr:Person {name: 'Meryl Streep', born: 1949})
CREATE (AnnieCorl:Person {name: 'Annie Corley', born: 1952})
CREATE (VictorSlez:Person {name: 'Victor Slezak', born: 1957})
CREATE
  (MerylStr)-[:ACTED_IN {roles: ['Francesca Johnson']}]->(TheBridges),
  (AnnieCorl)-[:ACTED_IN {roles: ['Carolyn Johnson']}]->(TheBridges),
  (VictorSlez)-[:ACTED_IN {roles: ['Michael Johnson']}]->(TheBridges),
MERGE (ClintE)-[:ACTED_IN {roles: ['Robert Kincaid']}]->(TheBridges)
MERGE (ClintE)-[:DIRECTED]->(TheBridges);

MATCH (m:Movie {title: 'The Bridges of Madison County'})-[r]-(p)
RETURN m, r, p;

-- Q 2

MATCH (justActor:Person)-[:ACTED_IN]->(m:Movie)<-[:ACTED_IN]-(both:Person)
WHERE exists( (both)-[:DIRECTED]->(m) )
RETURN  justActor.name as Actor, both.name as `bothActingDirecting`, m.title as Movie

-- Q3

MATCH (personOne:Person)-[:ACTED_IN]->(firstMovie:Movie)<-[:ACTED_IN]-(personTwo:Person)-[:ACTED_IN]->(secondMovie:Movie)<-[:ACTED_IN]-(firstPerson)
return personOne,firstMovie,secondMovie,personTwo;

-- Q4
MATCH (m:Movie)<-[:ACTED_IN]-(p:Person)
WITH m, collect(p) as actors
WHERE NONE (actor in actors WHERE actor.name = "Keanu Reeves" 
RETURN m

-- Q5
MATCH (m:Movie) 
WHERE n[$rating] < 70
delete m;
