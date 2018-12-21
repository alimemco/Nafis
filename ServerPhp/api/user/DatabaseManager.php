<?php

 require_once($_SERVER['DOCUMENT_ROOT']."/wp-load.php");

class DatabaseManager
{

    const DATABASE_NAME = "db";
    const HOST_NAME = "localhost";
    const USERNAME = "root";
    const PASSWORD = "";

    function userLogin($username, $password)
    {

        $connection = mysqli_connect(DatabaseManager::HOST_NAME, DatabaseManager::USERNAME, DatabaseManager::PASSWORD, DatabaseManager::DATABASE_NAME);
        $mysqlQuery = "SELECT * FROM wp_users WHERE user_login = '$username'";
        $result = mysqli_query($connection, $mysqlQuery);
        if ($result->num_rows > 0) {
            // $row = $result->fetch_array();
            //$databasePass = $row['user_pass'];
           
            $user = get_user_by( 'login', $username );
            if ( $user && wp_check_password( $password, $user->data->user_pass, $user->ID) ){
                return 1;
            }
            else {
                return 2;
            }

        } else {
            return 0;
        }
    }

    function userRegister($user_name,$user_pass,$user_email,$nickname,$display_name,$first_name,$last_name){
        require_once($_SERVER['DOCUMENT_ROOT']."/wp-load.php");

        $userData = array(
            'user_login'     =>  $user_name,
            'user_pass'      =>  $user_pass,
            'user_email'     =>  $user_email,
            'nickname'       =>  $nickname,
            'display_name'   =>  $display_name,
            'first_name'     =>  $first_name,
            'last_name'      => $last_name

        );

        $user_notExist = username_exists( $user_name );
        if (!$user_notExist){

            if ( !email_exists($user_email)){

                $user_id = wp_insert_user( $userData ) ;

                if ( ! is_wp_error( $user_id ) ) {
                    return 3;
                    //success
                }else{
                    return 2;
                    //error create user
                }

            }else{
                return 1 ;
                //email exists
            }

        }else{
            return 0;
            //user exists
        }

    }
    
        function getUserInfo($username){

        
$user = get_user_by( 'login', $username );

        $userData = array(
            'user_login'     =>  $user->user_login,
            'user_email'     =>  $user->user_email,
            'nickname'       =>  $user->nickname,
            'display_name'   =>  $user->display_name,
            'first_name'     =>  $user->first_name,
            'last_name'      =>  $user->last_name,
            'wp_capabilities'      =>  $user->wp_capabilities,
            'user_url'      =>  $user->user_url

        );
        
        return $userData;

    }



}