<?php // Root namespace for now. See Issue #85

use WC_API_Client;
use WC_API_Client_Exception;

$options = array(
    'debug'           => true,
    'return_as_array' => false,
    'validate_url'    => false,
    'timeout'         => 10000,
    'ssl_verify'      => false,
);

try {

	$client = new WC_API_Client( 'http://site.ir',
	'ck_656b634c5f5gc3f9b262d04f5g7402106fd1d321',
	'cs_2f09952a45f87c89ed73e239f8d46fc4d0720645',
	$options );

    // index
    //print_r($client->index->get());
    
    // For other endpoints, see example.php
    
} catch (WC_API_Client_Exception $e) {
    echo $e->getMessage() . PHP_EOL;
    echo $e->getCode() . PHP_EOL;

    if ($e instanceof WC_API_Client_HTTP_Exception) {
        print_r($e->get_request());
        print_r($e->get_response());
    }
}
