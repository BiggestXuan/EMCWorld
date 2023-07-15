<?php
if(!defined('IN_DISCUZ')){
    exit('Access Denied');
}
$dbhost = 'xxx';
$dbuser = 'xxx';
$dbpassword = 'xxx';
$connect = mysqli_connect($dbhost,$dbuser,$dbpassword,"xxx") or die("数据库连接失败");
$sql = "xxx";

define('NOROBOT',true);
if(empty($_G['uid'])){ ?>
<div id="ct" class="wp cl"> 
        <div class="mn bm cl"> 
                <div class="bm_c">
    <p style="width:300px; margin:200px auto; text-align:center;">你需要登陆后才能使用这个功能！</p>
    </div>
</div>
</div>
<?php     
}
else if($_G['groupid'] >= 4 & $_G['groupid'] <= 6){
    ?>
    <div id="ct" class="wp cl"> 
        <div class="mn bm cl"> 
                <div class="bm_c">
    <p style="width:300px; margin:200px auto; text-align:center;">您当前登陆的用户 <?php echo $_G['username']; ?> 无权使用该功能！</p> 
</div>
</div>
</div>
<?php 
}
else {
?>

<script src="../source/module/emcworld/spark-md5.js"></script>
<script src="../source/module/emcworld/md5.js"></script>
<div id="ct" class="wp cl"> 
        <div class="mn bm cl"> 
                <div class="bm_c"> 
                <p>欢迎使用 EMCWorld 整合包 MD5 码校验系统~</p>
                <p>意义：玩家在下载过程中会遇到各种下载源，无法保证玩家下载的整合包由官方渠道获取，因此通过本系统可以确定整合包是不是官方渠道获取。</p>
                <p>使用方法：将文件放到下面，查询输出的 MD5 是否和官方给出的一致。</p>
                <br />
                <p><input type="file" id="file"></input>
                <button type="button" onclick="a()">开始计算</button></p>
                <br>
                <p>MD5：<span id="md5">NULL</span><p>
                <br/>
                <p>EMCWorld 发行版本 MD5 码一览：</p>
                <table border="1" cellpadding="6">
                    <th width="100">EMCWorld 版本</th>
                    <th width="200"><center>MD5</center></th>
                    <th width="30"><center>结果</center></th>
                    <?php
                    $result = $connect -> query($sql);
                    if(!$result){
                        echo(mysqli_error($connect));
                        exit();
                    }
                    $index = 1;
                    if ($result -> num_rows > 0) {
                    while($row = $result->fetch_assoc()) {
                        $aaa = "<td id='v".$index."'>";
                        $bbb = "<td id='s".$index."'>";
                        ?>
                        <tr>
                            <td><?php echo $row["xxx"]; ?></td>
                            <?php echo $aaa.$row["xxx"]; ?></td>
                            <?php echo $bbb."<i class='fico-invalid fc-i'></i>"; ?></td></tr>
                            <?php
                            $index = $index + 1;
                            ?>
                        <?php 
                        }
                    }
                    ?>
                </table>
                </div> 
        </div> 
</div> 

    <?php
}

include template('emcworld/md5');

?>