-- Q 1
MATCH (ClintE:Person {name: 'Clint Eastwood'}) return ClintE; 
cREATE (TheBridges:Movie {
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
MATCH (keanu:Person {name: "Keanu Reeves"})-[:ACTED_IN]->(m:Movie)<-[:ACTED_IN]-(co_actor:Person)
WITH m.title AS movieTitle, collect(DISTINCT co_actor.name) AS coActors
RETURN movieTitle, coActors

-- Q5

MATCH (p:Person)-[r:REVIEWED]->(m:Movie)
WITH min(r.rating) AS minRating
MATCH (p:Person)-[r:REVIEWED]->(m:Movie)
WHERE r.rating = minRating
WITH m
LIMIT 1
DETACH DELETE m;

-- Import for next question
LOAD CSV WITH HEADERS FROM "file:///personalized_learning_dataset.csv" as row
MERGE (s:Student {
  studentId: row.Student_ID,
  age: toInteger(row.Age),
  gender: row.Gender,
  educationLevel: row.Education_Level,
  timeSpentOnVideos: toInteger(row.`Time_Spent_on_Videos`),
  quizAttempts: toInteger(row.Quiz_Attempts),
  quizScores: toFloat(row.Quiz_Scores) / 100,
  forumParticipation: toInteger(row.Forum_Participation),
  assignmentCompletionRate: toFloat(row.Assignment_Completion_Rate) / 100,
  engagementLevel: row.Engagement_Level,
  finalExamScore: toFloat(row.Final_Exam_Score) / 100,
  learningStyle: row.Learning_Style,
  feedbackScore: toFloat(row.Feedback_Score),
  dropOutChance: row.Dropout_Likelihood
})
MERGE (c:Course {name: row.Course_Name})
CREATE (s)-[:ENROLLED_IN]->(c);


-- 1.

match (s:Student) where s.gender = "Female" AND s.age < 20 AND s.learningStyle = "Visual" AND s.educationLevel = "High School" AND s.dropOutChance = "No" return s;

-- 2.

MATCH (s:Student)-[:ENROLLED_IN]->(c:Course)
WHERE s.engagementLevel <> "Low"
WITH c.name AS CourseName, s.educationLevel AS EducationLevel, MAX(s.assignmentCompletionRate) AS completionRate
RETURN DISTINCT CourseName, EducationLevel, MaxCompletionRate
ORDER BY completionRate DESC;

/*
1.List all female young female students, age below 20, prefer to visual learning style on their courses in high schooland they are unlikely to drop out their courses.

match student where gender female, age less 20, learningStyle = visual, educationalLevel = high scool, dropOutChance no

2.List all courses at various educational level that have been actively engaged by students and have highest assignments completion rate.

match course where student engagementLevel high,  assignmentCompletionRate > 60

3.Which course has received the highest feedback rating score?

    match course max feedback

4.How many male students have failed the cybersecurity final exam (below 40) due to the low engagement?

match count students where finalExamScore < 40 and engagementLevel low

5.How many students will likely to drop out their courses due to low engagement and list out their learning style and their educational level.
match count dropOutChance yes engagementLevel low, return count learning style, education level 
*/


