CREATE CONSTRAINT PersonIdConstraint IF NOT EXISTS
    FOR (p:Person)
REQUIRE p.id IS UNIQUE;

CREATE INDEX IF NOT EXISTS FOR (p:Person) ON (p.id);
CREATE INDEX IF NOT EXISTS FOR (f:Finances) ON (f.Income);
CREATE INDEX IF NOT EXISTS FOR (h:Health) ON (h.BMI);
CREATE INDEX IF NOT EXISTS FOR (d:Diet) ON (d.VegEater);
CREATE INDEX IF NOT EXISTS FOR (e:Education) ON (e.SchoolRange);

CALL apoc.mongo.find('mongodb://127.0.0.1:26060/diabetes.healthIndicators?authSource=admin',{},{limit:2500})
YIELD value
WITH value, apoc.util.generateStrongPassword(20) as uid // Generates a 20-character random string
MERGE (p:Person{id:uid})
    SET p.Gender = CASE value.Sex WHEN 1 THEN "M" WHEN 0 THEN "F" else "N/A" END,
        p.Age = toInteger(value.Age),
        p.Diabetes = CASE value.Diabetes_012
            when 0 then "None or just pregnant"
            when 1 then "pre"
            when 2 then "diabetic"
            else "n/a"
        end,
        p.generalHealth = toInteger(value.GenHlth),
        p.mentalHealth = toInteger(value.MentHlth),
        p.physicalHealth = toInteger(value.PhysHlth)

MERGE (edu:Education)
SET edu.SchoolRange = CASE value.Education
    when 1 then "none"
    when 2 then "elementary"
    when 3 then "high school partial"
    when 4 then "high school"
    when 5 then "partial college"
    when 6 then "full college"
    else "n/a"
end
MERGE (p)-[:HAS_EDUCATION]->(edu)

MERGE (fin:Finances)
SET fin.Income = CASE value.Income
    WHEN 1 then "<10K"
    WHEN 2 then "<15K"
    WHEN 3 then "<20K"
    WHEN 4 then "<25K"
    WHEN 5 then "<35K"
    WHEN 6 then "<50K"
    WHEN 7 then "<75K"
    WHEN 8 then ">75K"
    else "n/a"
end,
fin.Docprice = CASE value.NoDocbcCost WHEN 1 THEN "Skipped Doctor Due to Cost" WHEN 0 THEN "No Cost Issues" ELSE "N/A" END,
fin.Insurance = CASE value.AnyHealthcare WHEN 1 THEN "Has Healthcare" WHEN 0 THEN "No Healthcare" ELSE "N/A" END
MERGE(p)-[:HAS_FINANCE]->(fin)

MERGE (h:Health)
SET h.BMI = toInteger(value.BMI),
    h.HighBP = value.HighBP,
    h.HighColest = value.HighChol,
    h.Stroke = value.Stroke,
    h.HeartDis = value.HeartDiseaseorAttack,
    h.Smoker = value.Smoker,
    h.Alcoholic = value.HvyAlcoholConsump
MERGE (p)-[:HAS_HEALTH]->(h)

MERGE (d:Diet)
SET d.VegEater = value.Veggies,
    d.FruitEater = value.Fruits
MERGE (p)-[:HAS_DIET]->(d);

MATCH (p:Person)-[:HAS_EDUCATION]->(edu:Education)
MATCH (p)-[:HAS_FINANCE]->(fin:Finances)
MATCH (p)-[:HAS_HEALTH]->(h:Health)
MATCH (p)-[:HAS_DIET]->(d:Diet)
WHERE
    p.Age < 30
    AND p.Diabetes = "None or just pregnant"
    AND p.Gender = "F"

    AND h.BMI >= 19
    AND h.BMI <= 24

    AND edu.SchoolRange = "full college"

    AND fin.Income = "<75K"

    AND fin.Insurance = "Has Healthcare"

    AND p.generalHealth IN [1, 2]

    AND h.Smoker = 0
    AND h.Alcoholic = 0
    AND d.VegEater = 1
    AND d.FruitEater = 1

RETURN p, edu, fin, h, d