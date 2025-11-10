<?php
$current_default = $_COOKIE["defaultstudent"];
ini_set("display_errors", 1);
ini_set("display_startup_errors", 1);
error_reporting(E_ALL);
include "check.inc";
include "arrays.inc";
$studentIDX = $_GET["idx"];
$student = $students[$studentIDX];
$arr_size = count($students);
?>
<!DOCTYPE html>
<html lang="en">
    <head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="styles.css">
	<title>Student Number N Profile</title>
    </head>
    <body>
        <header class="container">
            <?php
            $represent_no = $studentIDX + 1;
            echo "<h1>Student No. {$represent_no}</h1>";
            ?>
        </header>
        <div class="container">
            <div class="flex-item">
                <?php echo "<a href='/studentRecords/setdefault.php?currentId={$student["id"]}'>Make default</a>"; ?>
            </div>
            <div class="flex-item">

                <?php echo "<img src='avatars/{$student["img"]}' alt='Profile Photo'>"; ?>
            </div>
            <div class="flex-item">
                <ul>
                   <li>
                       <?php echo "<p>Student Id: {$student["id"]}</p>"; ?>
                   </li>
                   <li>
                       <?php echo "<p>Address: {$student["address"]}</p>"; ?>
                   </li>
                </ul>
            </div>
            <div class="flex-item">
                <?php echo "<h2>Grade: {$student["mark"]}</h2>"; ?>
            </div>
            <div id="flex-sep">
                <?php
                $prev = $studentIDX - 1;
                $next = $studentIDX + 1;
                if ($prev < 0) {
                    echo "
                        <a href='/studentRecords/profile.php?idx={$next}'>Next</a>
                    ";
                } elseif ($next == $arr_size) {
                    echo "
                    <a href='/studentRecords/profile.php?idx={$prev}'>Back</a>
                ";
                } else {
                    echo "
                        <a href='/studentRecords/profile.php?idx={$prev}'>Back</a>
                        <a href='/studentRecords/profile.php?idx={$next}'>Next</a>
                    ";
                }
                ?>
            </div>
        <?php include "footer.inc"; ?>
        </div>
  </body>
</html>
