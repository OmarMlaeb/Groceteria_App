<?php

include("db_info.php");

$first_name = $_POST["first_name"]; // list that contains all the first names sent from the front-end
$last_name = $_POST["last_name"]; // list that contains all the last names sent from the front-end
$mobile_number = $_POST["mobile_number"]; // list that contains all the mobile numbers sent from the front-end
$email = $_POST["email"]; // list that contains all the emails sent from the front-end
$date_of_birth = $_POST["date_of_birth"]; // list that contains all the birth dates sent from the front-end
$currency = $_POST["currency"]; // list that contains all the currencies sent from the front-end

$query = $mysqli->prepare("INSERT INTO profiles (first_name, last_name, mobile_number, email, date_of_birth, currency) VALUES (?, ?, ?, ?, ?, ?)"); // to insert values to the table "profiles" showing the profile of the user 
// the values are "?" to prevent sql injections

$query->bind_param("ssiss", $first_name, $last_name, $mobile_number, $email, $date_of_birth, $currency); // binds the parameters to the SQL query and tells the database what the parameters are

$query->execute(); // the database executes the statement after binding the values to the parameters

$response = [];

$response["status"] = "Congrats: Added Profile"; // check if response succeeded

$json_response = json_encode($response); // encode the string to JSON format

echo $json_response; // return the json response

?>