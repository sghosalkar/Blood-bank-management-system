<?php

$bloodBankId = $_POST['bloodBankId'];

//$bloodBankId = 2;

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

	$stmt = $conn->prepare("SELECT transactions.name, transactions.contact_no, transactions.type, transactions.blood_group, transactions.quantity, transactions.price, date_table.day, date_table.month, date_table.year FROM transactions, date_table WHERE bloodbank_id = :bloodBankId AND transactions.date_id = date_table.id");

	$stmt->bindParam(':bloodBankId', $bloodBankId);

	$stmt->execute();

	if($stmt->rowCount() > 0) {
		$data = $stmt->fetchAll(PDO::FETCH_ASSOC);
		$closeData = array("name"=>"close");
		$closeArray = array($closeData);
		$combine = array_merge($data,$closeArray);
		echo json_encode($combine);
	}
	else {
		$json['success'] = 0;
		$json['message'] = 'No Data found';		
		$json['myintro'] = '';

		echo "{".json_encode($json)."}";
	}
	$conn = null;
?>