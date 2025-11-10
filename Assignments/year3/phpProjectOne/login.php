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
	<title>Login To Student Profiles</title>
    </head>
    <body>
        <header class="container">
            <h1>Login</h1>
        </header>
        <div class="container">
        <div class="flex-item">
            <p>
                You must enter a passcode to enter this site
            </p>
        </div>
        <div class="flex-item">
          <form action="/studentRecords/loginLogic.php" method="POST">
              <label for="passcode">
                Passcode:
              </label>
              <input type="password" name="passcode" id="passcode">
            <input type="submit" value="Submit">
          </form>
        </div>
    </div>
  </body>
</html>
