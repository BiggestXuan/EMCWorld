<?php
if(!defined('IN_DISCUZ')){
    exit('Access Denied');
}
define('NOROBOT',true);
?>

<div id="ct" class="wp cl"> 
        <div class="mn bm cl"> 
                <div class="bm_c">
                    <strong><center><font size="5">EMCWorld 赞助者列表</font></center></strong>
                    <br/>
                    <center>
                    <table border="2" cellpadding="6">
                        <th width="50"><font size="3"><strong>序号</strong></font></th>
                        <th width="100"><font size="3"><strong>玩家名</strong></font></th>
                        <th width="85"><font size="3"><strong>赞助者</strong></font></th>
                        <th width="85"><font size="3"><strong>高级赞助者</strong></font></th>
                        <th width="85"><font size="3"><strong>顶级赞助者</strong></font></th>
                        <th width="85"><font size="3"><strong>吉祥物</strong></font></th>
                        <th width="85"><font size="3"><strong>开发者</strong></font></th>
                        <th width="85"><font size="3"><strong>定制</strong></font></th>
                        <th width="85"><font size="3"><strong>可继承</strong></font></th>
                        <?php 
                            $dbhost = 'xxx';
                            $dbuser = 'xxx';
                            $dbpassword = 'xxx';
                            $connect = mysqli_connect($dbhost,$dbuser,$dbpassword,"xxx") or die("数据库连接失败");
                            $sql = "xxx";
                            $yes = "<td><i class='fico-valid fc-v'></i></td>";
                            $no = "<td><i class='fico-invalid fc-i'></i></td>";
                            $result = $connect ->query($sql);
                            if(!$result){
                                echo(mysqli_error($connect));
                                exit();
                            }
                            while($row = $result->fetch_assoc()) {
                                $level = $row["xxx"];
                                ?><tr><?php
                                echo "<td>".$row["xxx"]."</td>";
                                echo "<td>".$row["xxx"]."</td>";
                                echo strpos($level,"1") !== false ? $yes : $no;
                                echo strpos($level,"2") !== false ? $yes : $no;
                                echo strpos($level,"3") !== false ? $yes : $no;
                                echo strpos($level,"4") !== false ? $yes : $no;
                                echo strpos($level,"5") !== false ? $yes : $no;
                                echo intval(substr($level,-1,1)) >= 6 ? $yes : $no;
                                echo strpos($row["xxx"],"1") !== false ? $yes : $no;
                                ?></tr>
                                <?php
                            }
                        ?>
                    </table>
                    </center>
                    <br/>
                    <hr/>
                    <br/>
                        <p>相关说明：</p>
                        <p>1.上表和 EMCWorld 中的赞助者同步，如果修改了名称请及时通知大轩更新。</p>
                        <p>2.可继承是指是否在以后大轩主导制作的整合包享受同等权益。</p>
                        <p>3.大轩整合包中的任意赞助永久可继承，EMCWorld 仅 288.88 元（或 49.99 USD）以上的赞助可继承。（老玩家福利）</p>
                        <p>4.大轩整合包赞助若在两个等级之间，取更高等级；EMCWorld 赞助若在两个等级之间，取更低等级。（老玩家福利）</p>
                        <p>5.在 EMCWorld 公测之前存在贡献的吉祥物玩家也可永久继承。</p>
                        <p>6.有关赞助其他事宜，包括退款等情况，请参见 <a href="https://biggestxuan.top/emcworld.php?mod=license" target="_blank">EMCWorld 使用协议。</a></p>
                </div>
        </div>
</div>
<?php
include template('emcworld/sponsors');
?>