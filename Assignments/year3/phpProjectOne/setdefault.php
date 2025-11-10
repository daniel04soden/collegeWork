<?php
$new_student_id = $_GET["currentId"];
setcookie("defaultstudent", $new_student_id, time() + 3600 * 24);

ini_set("display_errors", 1);
ini_set("display_startup_errors", 1);
error_reporting(E_ALL);
include "check.inc";
include "arrays.inc";
$i = -1;
foreach ($students as $student) {
    $i++;
    if ($student["id"] === $new_student_id) {
        $myStudent = $student;
        break;
    }
}
?>

<!DOCTYPE html>
<html lang="en">
    <head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="styles.css">
	<?php echo "<title>Student $i set as default</title>"; ?>
    </head>
    <body>
        <header class="container">
            <h1>Default</h1>
        </header>
        <div class="container">
            <div class="flex-item">
                <p>Default saved</p>
                <?php echo "<img src='avatars/{$myStudent["img"]}' alt='Logo'>"; ?>
            </div>
            <div class="flex-item">
                <?php include "footer.inc"; ?>
            </div>
        </div>
  </body>
</html>
