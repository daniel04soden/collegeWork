<?php
ini_set("display_errors", 1);
ini_set("display_startup_errors", 1);
error_reporting(E_ALL);

include "check.inc";
include "arrays.inc";
?>

<!DOCTYPE html>
<html lang="en">
    <head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="styles.css">
	<title>Student Profile Viewer</title>
    </head>
    <body>
        <div class="container">

        <div class="flex-item">
            <h1>Search</h1>
        </div>
    <div class="flex-item">
        <ul>
            <li>
                <p>Min Mark</p>
                <input id="minRange" type="range" min="0" max="100" value="0"onchange="minSlider()"/>
                <label class="min-range-label" for="minRange">0</label>
            </li>
            <li>
                <p>Max Mark</p>
                <input id="maxRange" type="range" min="0" max="100" value="0" onchange="maxSlider()"/>
                <label class="max-range-label" for="maxRange">0</label>
            </li>
        </ul>
    </div>
    <div class="flex-item">
        <div>
            <ul id="arr-students-marks">
            <?php foreach ($students as $student) {
                echo "<a href='/studentRecords'><img class='imgSearching' src='avatars/{$student["img"]}' alt='Logo'></a>";
            } ?>
            </ul>
        </div>
    </div>
    <div class="flex-item">
        <?php include "footer.inc"; ?>
    </div>
    </div>
    <script src="index.js"></script>
  </body>
</html>
