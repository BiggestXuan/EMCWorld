<?php
include "token.php";
include "utils.php";

$token = x;
$state = 100;
$o = new BotToken($token);

if($o -> getPermission() == 0){
    $state = 101;
}

if(!isset($_GET["key"]) && $state == 100){
    $state = 102;
}

if($state >= 101 && $state <= 102){
    exit("{
        \"state\":".$state."
    }");
}
$key = $_GET["key"];
$userid = decodeID($key);
$r = new UserResult();
$r -> setUserID($userid);
if($userid != 0){
    $re = getKeyResult($userid);
    $used = $re["used"];
    $r -> setName(getName($userid));
    $r -> setCreateTime(intval(decodeTime($key)));
    $r -> setDataTime(intval(addFirstTime($userid)));
    $r -> setUsed(intval($used));
    if($used == 1){
        $r -> setAdminID(x);
        $r -> setUseTime(x);
    }else{
        setKeyUsed($userid,$o -> getName(),time());
    }
}
echo json_encode($r,JSON_UNESCAPED_UNICODE);
?>