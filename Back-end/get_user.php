<?php

include("db_info.php");

$mobile_number = $_GET["mobile_number"]; // list that contains all the mobile numbers sent from the front-end

$query = $mysqli->prepare("SELECT * FROM signup_accounts WHERE mobile_number = ?"); // to select values from the table "signup_accounts" and checks if user mobile number is signed in before

$query->bind_param("i", $mobile_number); // binds the parameters to the SQL query and tells the database what the parameters are

$query->execute();  // the database executes the statement after binding the values to the parameters

$array = $query->get_result(); // retrieves a result set from the prepared statement as an object

while($user_name = $array->fetch_assoc()){ // for every single row in the table fetch for the result and return only the first name as response  
    $response = array("Response"=>$user_name["first_name"]);
}

$json_response = json_encode($response); //encode the string to JSON format and return the json response

echo $json_response;

?>