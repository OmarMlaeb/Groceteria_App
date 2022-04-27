<?php

include("db_info.php");

$mobile_number = $_GET["mobile_number"]; // list that contains all the first names sent from the front-end
$password = $_GET["password"]; // list that contains all the last names sent from the front-end

$query = $mysqli->prepare("SELECT * FROM signup_accounts WHERE mobile_number = '$mobile_number' AND password = '$password'"); // to select values from the table "signup_accounts" and checks if user has an account already while signing in to the app 

$query->bind_param("is", $mobile_number, $password); // binds the parameters to the SQL query and tells the database what the parameters are

$query->execute(); // the database executes the statement after binding the values to the parameters

$array = $query->get_result(); // retrieves a result set from the prepared statement as an object

while($account = $array->fetch_assoc()){ // for every single row in the table fetch for the result and reserve it in "account" object  
    $response = array("Response"=>"Login Success!");
}

$json_response = json_encode($response); // encode the string to JSON format

echo $json_response; // return the json response

?>