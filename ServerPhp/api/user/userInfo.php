<?php

$json = file_get_contents('php://input');
$userInfo = json_decode($json);
$username = $userInfo->username;

include 'DatabaseManager.php';
$databaseMan = new DatabaseManager();
$userInfo=$databaseMan->getUserInfo($username);
echo json_encode(['userInfo'=>$userInfo]);