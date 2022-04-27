<?php

include("db_info.php");

$first_name = $_GET["first_name"]; // list that contains all the first names sent from the front-end
$last_name = $_GET["last_name"]; // list that contains all the last names sent from the front-end
$mobile_number = $_GET["mobile_number"]; // list that contains all the mobile numbers sent from the front-end
$email = $_GET["email"]; // list that contains all the emails sent from the front-end
$date_of_birth = $_GET["date_of_birth"]; // list that contains all the birth dates sent from the front-end
$password = $_GET["password"]; // list that contains all the passwords sent from the front-end
$confirm_password = $_GET["confirm_password"]; // list that contains all the confirmed passwords sent from the front-end

$valid_email = filter_var($email, FILTER_VALIDATE_EMAIL); // check if email is valid

if($valid_email === false) {
    $response = array("Response"=>"Email address is invalid"); // return message if the email is invalid
} else {

    $query = $mysqli->prepare("INSERT INTO signup_accounts (first_name, last_name, mobile_number, email, year_of_birth, password, confirm_password) VALUES (?, ?, ?, ?, ?, ?, ?)");  // to insert values to the table "signup_accounts" when the user sign up in the app 

    $query->bind_param("ssisiss", $first_name, $last_name, $mobile_number, $email, $year_of_birth, $password, $confirm_password); // binds the parameters to the SQL query and tells the database what the parameters are

    $query->execute(); // the database executes the statement after binding the values to the parameters

    $response = array("Response"=>"Created Account Successfully!"); // check if response succeeded

}

$json_response = json_encode($response); // encode the string to JSON format

echo $json_response; // return the json response

?>