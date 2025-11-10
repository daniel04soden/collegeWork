<?php
include "arrays.inc";
if (!isset($_COOKIE["defaultstudent"])) {
    $default_student_id = $students[0]["id"];
    setcookie("defaultstudent", $default_student_id, time() + 3600 * 24);
}
ini_set("display_errors", 1);
ini_set("display_startup_errors", 1);
error_reporting(E_ALL);
include "check.inc";
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
            <h1>Student Profile Viewer</h1>
        </div>
    <div class="flex-item">
        <h2>Default Student</h2>
        <?php
        $j = -1;
        foreach ($students as $student) {
            $j++;
            if ($student["id"] === $_COOKIE["defaultstudent"]) {
                $myStudent = $student;
                break;
            }
        }
        echo "<a href='/studentRecords/profile.php?idx={$j}'><img src='avatars/{$myStudent["img"]}' alt='Logo'></a>";
        ?>
    </div>
    <div class="flex-item">
        <div>
            <?php
            $i = -1;
            foreach ($students as $student) {
                $i++;
                echo "<a href='/studentRecords/profile.php?idx={$i}'><img src='avatars/{$student["img"]}' alt='Logo'></a>";
            }
            ?>
        </div>
    </div>
    <div class="flex-item">
        <?php include "footer.inc"; ?>
    </div>
    </div>
    <script src="index.js"></script>
  </body>
</html>
