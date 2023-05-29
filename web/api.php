<?php
$ip = $_SERVER['REMOTE_ADDR'];
$timestamp = time();
$logFile = "/file.txt";
$logString = $ip . ":" . $timestamp . "\n";
file_put_contents($logFile, $logString, FILE_APPEND);

$ip = $_SERVER['REMOTE_ADDR'];
$logFile = "/file.txt";
$logData = file_get_contents($logFile);

$logLines = explode("\n", $logData);
$count = 0;
foreach ($logLines as $line) {
  if (!$line) {
    continue;
  }
  list($lineIp, $lineTimestamp) = explode(":", $line);
  if ($ip === $lineIp && (time() - $lineTimestamp) < 60) {
    $count++;
  }
}

if ($count > 20) {
    echo "[ERROR] Exceeded the number of requests in a short period of time!";
    exit();
}

$version = 7;
$versionName = "0.5.2";
$index = isset($_GET["version"])?$_GET["version"]:NULL;
$name = isset($_GET["name"])?$_GET['name']:NULL;
$uuid = isset($_GET["uuid"])?$_GET["uuid"]:NULL;
$dbhost = '???';
$dbuser = '???';
$dbpassword = '???';
$connect = new mysqli($dbhost,$dbuser,$dbpassword,"emcworld");
if(($index == NULL & $name == NULL & $uuid == NULL) | (($name == NULL & $uuid != NULL) | ($name != NULL & $uuid == NULL))){
    header("Location:https://www.bilibili.com/video/BV1GJ411x7h7/");
    exit();
}
if($index != NULL){
    echo $version.",".$versionName;
}

$sql = "SELECT level FROM sponsors WHERE (playerName=?) AND (uuid=?)";
$stmt = $connect->stmt_init();
if ($stmt->prepare($sql) & $index == NULL) {
    $stmt->bind_param("ss",$name, $uuid);
    $stmt->execute();
    $stmt->bind_result($level);
    $stmt->fetch();
    if($level == NULL){
        echo 0;
    }
    echo $level;


    $stmt->close();
}
$connect->close();
?>