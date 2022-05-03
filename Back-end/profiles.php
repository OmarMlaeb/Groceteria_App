<?php

include("db_info.php");

$mobile_number = $_GET["mobile_number"]; // list that contains all the mobile numbers sent from the front-end

$query = $mysqli->prepare("SELECT * FROM signup_accounts WHERE mobile_number = ?"); // to select values from the table "signup_accounts" and checks if user mobile number is signed in before
// the values are "?" to prevent sql injections

$query->bind_param("i", $mobile_number); // binds the parameters to the SQL query and tells the database what the parameters are

$query->execute(); // the database executes the statement after binding the values to the parameters

$array = $query->get_result(); // retrieves a result set from the prepared statement as an object

while($account = $array->fetch_assoc()){ // for every single row in the table fetch for the result and return the first name and last name and mobile number and email and year of birth as response  
    $response_1= array("First name"=>$account["first_name"]);
    $response_2 = array("Last name"=>$account["last_name"]);
    $response_3 = array("Mobile Number"=>$account["mobile_number"]);
    $response_4 = array("Email"=>$account["email"]);
    $response_5 = array("Year Of Birth"=>$account["year_of_birth"]);
}

// for each encode the string to JSON format and return the json response
$json_response_1 = json_encode($response_1);
echo $json_response_1;

$json_response_2 = json_encode($response_2);
echo $json_response_2;

$json_response_3 = json_encode($response_3);
echo $json_response_3;

$json_response_4 = json_encode($response_4);
echo $json_response_4;

$json_response_5 = json_encode($response_5);
echo $json_response_5;

?>