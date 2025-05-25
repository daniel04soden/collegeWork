db = db.getSiblingDB("config"); // Connecting to config server

var mongosConn = db;

var res = null;

db = connect("localhost:26050/diabetes"); // Connect to location of config server

// Init replica set for config server
res = rs.initiate({
  _id: "cfg",
  members: [
    { _id: 0, host: "localhost:" + "26050"},
    { _id: 1, host: "localhost:" + "26051"},
    { _id: 2, host: "localhost:" + "26052"},
  ],
});

while (res.ok != 1) {
  sleep(10000);
}
print("Replica Set metadata Created!");
while (
  (rs.status().members[0].state != 1 && rs.status().members[0].state != 2) ||
  (rs.status().members[1].state != 1 && rs.status().members[1].state != 2) ||
  (rs.status().members[2].state != 1 && rs.status().members[2].state != 2)
) {
  sleep(10000);
}
print("Replica Set metadata Up!");

// North replica set to be sharded
db = connect("localhost:27000/diabetes");

res = rs.initiate({
  _id: "north",
  members: [
    { _id: 0, host: "localhost:" + "27000" },
    { _id: 1, host: "localhost:" + "27001" },
    { _id: 2, host: "localhost:" + "27002" },
  ],
});

while (res.ok != 1) {
  sleep(10000);
}
print("Replica Set for North Created!");
while (
  (rs.status().members[0].state != 1 && rs.status().members[0].state != 2) ||
  (rs.status().members[1].state != 1 && rs.status().members[1].state != 2) ||
  (rs.status().members[2].state != 1 && rs.status().members[2].state != 2)
) {
  sleep(10000);
}
print("Replica Set replset1 Up!");

// South replica set to be sharded
db = connect("localhost:27100/diabetes");

res = rs.initiate({
  _id: "south",
  members: [
    { _id: 0, host: "localhost:" + "27100" },
    { _id: 1, host: "localhost:" + "27101" },
    { _id: 2, host: "localhost:" + "27102" },
  ],
});

while (res.ok != 1) {
  sleep(10000);
}
print("Replica Set replset2 Created!");
while (
  (rs.status().members[0].state != 1 && rs.status().members[0].state != 2) ||
  (rs.status().members[1].state != 1 && rs.status().members[1].state != 2) ||
  (rs.status().members[2].state != 1 && rs.status().members[2].state != 2)
) {
  sleep(10000);
}
print("Replica Set replset2 Up!");

// East replica set to be sharded

db = connect("localhost:27200/diabetes");


res = rs.initiate({
  _id: "east",
  members: [
    { _id: 0, host: "localhost:" + "27200" },
    { _id: 1, host: "localhost:" + "27201" },
    { _id: 2, host: "localhost:" + "27202" },
  ],
});

while (res.ok != 1) {
  sleep(10000);
}
print("Replica Set replset3 Created!");
while (
  (rs.status().members[0].state != 1 && rs.status().members[0].state != 2) ||
  (rs.status().members[1].state != 1 && rs.status().members[1].state != 2) ||
  (rs.status().members[2].state != 1 && rs.status().members[2].state != 2)
) {
  sleep(10000);
}
print("Replica Set replset3 Up!");

// West replica set to be sharded
db = connect("localhost:27300/diabetes");

res = rs.initiate({
  _id: "west",
  members: [
    { _id: 0, host: "localhost:" + "27300" },
    { _id: 1, host: "localhost:" + "27301" },
    { _id: 2, host: "localhost:" + "27302" },
  ],
});


while (res.ok != 1) {
  sleep(10000);
}
sleep(2000)

print("Replica Set replset4 Created!");
while (
  (rs.status().members[0].state != 1 && rs.status().members[0].state != 2) ||
  (rs.status().members[1].state != 1 && rs.status().members[1].state != 2) ||
  (rs.status().members[2].state != 1 && rs.status().members[2].state != 2)
) {
  sleep(10000);
}
print("Replica Set replset4 Up!");

// Quit
quit();
