# How to install the apoc jar - macos

cd /opt/homebrew/Cellar/neo4j/2025.04.0/libexec/plugins/ # With homebrew
# Download a neo4j jar from https://github.com/neo4j/apoc/releases/download/2025.04.0/apoc-2025.04.0-core.jar
mv ~/Downloads/apoc-2025.04.0-core.jar .
neo4j restart
neo4j start
cypher-shell # Check if still able to login
# Go to browser and login at http://localhost:7474 and run this command to confirm working:
```sql
-- Wrapped as sql for small bit of syntax highlighting 
SHOW PROCEDURES YIELD name
WHERE name STARTS WITH 'apoc'
RETURN name;
```
