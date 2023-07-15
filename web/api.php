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

if ($count > 30) {
    echo "[ERROR] Exceeded the number of requests in a short period of time!";
    exit();
}

$version = 12;
$versionName = "1.0.0";
$index = isset($_GET["version"]) ? $_GET["version"] : NULL;
$name = isset($_GET["name"]) ? $_GET['name'] : NULL;
$uuid = isset($_GET["uuid"]) ? $_GET["uuid"] : NULL;
$dbhost = 'xxx';
$dbuser = 'xxx';
$dbpassword = 'xxx';
$connect = new mysqli($dbhost,$dbuser,$dbpassword,"xxx");
if(($index == NULL & $name == NULL & $uuid == NULL) | (($name == NULL & $uuid != NULL) | ($name != NULL & $uuid == NULL))){
    header("Location:https://www.bilibili.com/video/BV1GJ411x7h7/");
    exit();
}
if($uuid != NULL && strpos($uuid,"select") !== false){
    echo "Access Denied!";
    exit();
}
if($index != NULL){
    echo $version.",".$versionName;
}

$sql = "xxx";
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