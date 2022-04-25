<?php

include("db_info.php");

$mobile_number = $_POST["mobile_number"]; // list that contains all the mobile numbers sent from the front-end
$number_of_products = $_POST["number_of_products"]; // list that contains all the number_of_products sent from the front-end
$subtotal = $_POST["subtotal"]; // list that contains all the subtotal prices sent from the front-end
$delivery = $_POST["delivery"]; // list that contains all the delivery prices sent from the front-end
$total = $_POST["total"]; // list that contains all the total prices of all bills sent from the front-end

$query = $mysqli->prepare("INSERT INTO bills (mobile_number, number_of_products, subtotal, delivery, total) VALUES (?, ?, ?, ?, ?)"); // to insert values to the table "bills" when the user buy products and has a bill in the app 
// the values are "?" to prevent sql injections

$query->bind_param("iiddd", $mobile_number, $number_of_products, $subtotal, $delivery, $total); // binds the parameters to the SQL query and tells the database what the parameters are

$query->execute(); // the database executes the statement after binding the values to the parameters

$response = [];

$response["status"] = "Congrats: Added Bill Successfully"; // check if response succeeded

$json_response = json_encode($response); // encode the string to JSON format

echo $json_response; // return the json response

?>