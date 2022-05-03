<?php

include("db_info.php");

$user_mobile_number = $_GET["user_mobile_number"]; // list that contains all the user mobile number sent from the front-end
$building_name = $_GET["building_name"]; // list that contains all the building names sent from the front-end
$apartment = $_GET["apartment"]; // list that contains all the apartment sent from the front-end
$delivery_instructions = $_GET["delivery_instructions"]; // list that contains all the delivery instructions sent from the front-end
$receiver_name = $_GET["receiver_name"]; // list that contains all the receiver names sent from the front-end
$receiver_mobile_number = $_GET["receiver_mobile_number"]; // list that contains all the receiver mobile number sent from the front-end

$query = $mysqli->prepare("INSERT INTO addresses (user_mobile_number, building_name, apartment, delivery_instructions, receiver_name, receiver_mobile_number) VALUES (?, ?, ?, ?, ?, ?)"); // to insert values to the table "addresses" when the adds an address in the app 

$query->bind_param("issssi", $user_mobile_number, $building_name , $apartment, $delivery_instructions, $receiver_name, $receiver_mobile_number); // binds the parameters to the SQL query and tells the database what the parameters are

$query->execute(); // the database executes the statement after binding the values to the parameters

$response = array("Response"=>"Added Address Successfully!"); // check if response succeeded

$json_response = json_encode($response); // encode the string to JSON format

echo $json_response; // return the json response

?>