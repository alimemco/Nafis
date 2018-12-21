<?php

require_once($_SERVER['DOCUMENT_ROOT']."/wp-load.php");
$username="username";
$pass="password";
$user = get_user_by( 'login', $username );
if ( $user && wp_check_password( $pass, $user->data->user_pass, $user->ID) )
   echo "That's it";
else
   echo "Nope";

