<?php
session_start();
$plaintext_password = $_POST["passcode"];
$passcodes = ["12345", "ABCDEF", "54321", "FEDCBA"];

if (in_array($plaintext_password, $passcodes, false)) {
    $_SESSION["loggedin"] = true;
    header("Location: index.php");
    exit();
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Trying to Log In...</title>
</head>
<body>
    <h1>Failed</h1>
    <a href="/studentRecords/login.php">Try again</a>
</body>
</html>
