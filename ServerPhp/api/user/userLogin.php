<?php

$json = file_get_contents('php://input');
$userInfo = json_decode($json);
$username = $userInfo->username;
$password = $userInfo->password;

include 'DatabaseManager.php';
$databaseMan = new DatabaseManager();
$status=$databaseMan->userLogin($username,$password);
echo json_encode(['status'=>$status]);