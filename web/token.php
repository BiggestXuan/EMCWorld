<?php

function getTokenPermission($value,$type){
    $dbhost = 'x';
    $dbuser = 'x';
    $dbpassword = 'x';
    $connect = mysqli_connect($dbhost,$dbuser,$dbpassword,"x") or die("数据库连接失败");
    $sql = "x";
    $result = $connect -> query($sql);
    $r = $result->fetch_assoc();
    if($type == 1){
       return isset($r) ? x : 0; 
    }else{
        return isset($r) ? x : 0; 
    }
}

class BotToken{
    var $value;

    function __construct($value){
        $this -> value = $value;
    }

    function getValue(){
        return $this->value;
    }

    function getPermission(){
        return getTokenPermission($this->value,1);
    }

    function getName(){
        return getTokenPermission($this->value,2);
    }
}

#[\AllowDynamicProperties]
final class UserResult{
    var $state = 100;
    var $userid;
    var $name;
    var $createtime;
    var $datatime;
    var $used;
    var $admin;
    var $usetime;
    
    function __construct(){

    }

    function setUserID($id){
        $this -> userid = $id;
        if($id == 0){
            $this -> createtime = 0;
            $this -> datatime = 0;
            $this -> used = 1;
            $this -> admin = 0;
            $this -> usetime = 0;
            $this -> name = "null";
        }
    }

    function setName($name){
        $this -> name = $name;
    }

    function setCreateTime($time){
        $this -> createtime = $time;
    }

    function setDataTime($time){
        $this -> datatime = $time;
    }

    function setUsed($used){
        $this -> used = $used;
        if($used == 0){
            $this -> admin = 0;
            $this -> usetime = 0;
        }
    }

    function setAdminID($id){
        $this -> admin = $id;
    }

    function setUseTime($time){
        $this -> usetime = $time;
    }
}
?>