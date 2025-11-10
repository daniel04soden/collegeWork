<?php
ini_set("display_errors", 1);
ini_set("display_startup_errors", 1);
error_reporting(E_ALL);
?>
<!DOCTYPE html>
<html lang="en">
    <head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="styles.css">
	<title>Logged out</title>
    </head>
    <body>
        <header class="container">
            <h1>Logged Out</h1>
        </header>
        <div class="container">
            <div class="flex-item">
               <p>You are now logged out</p>
            </div>
            <div class="flex-item">
                Click here to log back in <a href="/studentRecords/login.php">Link</a>
            </div>
        </div>
  </body>
</html>
