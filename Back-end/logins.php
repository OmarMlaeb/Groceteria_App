<?php

include("db_info.php");

$mobile_number = $_GET["mobile_number"]; // list that contains all the first names sent from the front-end
$password = $_GET["password"]; // list that contains all the last names sent from the front-end

$query = $mysqli->prepare("SELECT * FROM signup_accounts WHERE mobile_number = ? AND password = ?"); // to select values from the table "signup_accounts" and checks if user has an account already while signing in to the app 

$query->bind_param("is", $mobile_number, $password); // binds the parameters to the SQL query and tells the database what the parameters are

$query->execute(); // the database executes the statement after binding the values to the parameters

$array = $query->get_result(); // retrieves a result set from the prepared statement as an object

while($array->fetch_assoc()){ // for every single row in the table fetch for the result and return a response of mobile number and password matched   
    $response = array("Response"=>"Login Success!");

    $json_response = json_encode($response); // encode the string to JSON format

    echo $json_response; // return the json response
}

$response = array("Response"=>"Login Error!"); // else if the mobile number or password are wrong return error message

echo json_encode($response); // return the json response

?>