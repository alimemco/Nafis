<?php

$json = file_get_contents('php://input');
$userInfo = json_decode($json);
$username       = $userInfo->username;
$password       = $userInfo->password;
$email          = $userInfo->email;
$nickname       = $userInfo->nickname;
$display_name   = $userInfo->display_name ;
$first_name     = $userInfo->first_name;
$last_name      = $userInfo->last_name;

include 'DatabaseManager.php';
$databaseMan = new DatabaseManager();
$status=$databaseMan->userRegister($username,$password,$email,$nickname,$display_name,$first_name,$last_name);
echo json_encode(['status'=>$status]);
