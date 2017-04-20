<?php

require_once('credentials.php');

$servername = HOST;
$username = USER;
$password = PASS;
$dbname = DB;
try
{
	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username,$password);
}
catch(PDOException $e) {
	echo "Not connected";
}

?>