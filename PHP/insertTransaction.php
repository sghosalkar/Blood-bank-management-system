<?php
$bloodBankId = $_POST['bloodBankId'];
$name = $_POST['name'];
$contactNo = $_POST['contactNo'];
$type = $_POST['type'];
$bloodGroup = $_POST['bloodGroup'];
$quantity = $_POST['quantity'];
$price = $_POST['price'];
$day = $_POST['day'];
$month = $_POST['month'];
$year = $_POST['year'];

try
{
	require_once('credentials.php');

	$servername = HOST;
	$username = USER;
	$password = PASS;
	$dbname = DB;
	$conn = mysqli_connect($servername,$username,$password,$dbname);

	if (!$conn) {
    	echo "<div>Error: " . $sql . "<br>" . mysqli_connect_error()."</div>";
    	die("Connection failed: " . mysqli_connect_error());
	}

	$sql = "SELECT * FROM date_table WHERE day = '$day' AND month = '$month' AND year = '$year'";
	$result = mysqli_query($conn,$sql);

	if (mysqli_num_rows($result) == 0) {
		$sql = "INSERT INTO date_table (day, month, year)
		VALUES('$day','$month','$year')";

		if (mysqli_query($conn, $sql)) {
    		echo "<div>New record created successfully</div>";
		} else {
    		echo "<div>Error: " . $sql . "<br>" . mysqli_error($conn)."</div>";
		}
	}
	$sql = "SELECT * FROM date_table WHERE day = '$day' AND month = '$month' AND year = '$year'";
	$result = mysqli_query($conn,$sql);

	$row = mysqli_fetch_assoc($result);
	$dateId = $row["id"];
		
	$sql = "INSERT INTO transactions (bloodbank_id, name, contact_no, type, blood_group, quantity, price, date_id)
	VALUES('$bloodBankId','$name','$contactNo','$type','$bloodGroup','$quantity','$price','$dateId')";
	if (mysqli_query($conn, $sql)) {
    	echo "<div>New record created successfully</div>";
	} else {
    	echo "<div>Error: " . $sql . "<br>" . mysqli_error($conn)."</div>";
	}
	mysqli_close($conn);
}
catch(Exception $e)
{
	echo $sql . "<br>" . $e->getMessage();
}


?>
