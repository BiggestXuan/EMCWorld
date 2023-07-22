<?php
if(!defined('IN_DISCUZ')){
    exit('Access Denied');
}
include "utils.php";
define('NOROBOT',true);?>
<style>
    kc {
        color:#4682B4;
        font-size:26px;
    }
    pp{
        font-size:17px;
        line-height:3;
        display: inline-block;
    }
    h1{
        font-size:22px;
        text-align:center;
    }
    h2{
        font-size:20px;
    }
    ti{
        font-size:20px;
        color:red;
    }
    to{
        font-size:20px;
        color:#4682B4;
    }
    gre{
        font-size:16px;
        color:#00cd00;
    }
</style>
<?php

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
    else if($_G['groupid'] >= 4 & $_G['groupid'] <= 8){
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

<div id="ct" class="wp cl"> 
        <div class="mn bm cl"> 
                <div class="bm_c">
                    <h1>Q 群验证编码系统</center></h1>
                <pp>QQ 群：573239523（进群会需要验证当前页面密钥，请在入群答案填写下面一串由字母和数字组成的内容）</pp>
                <br />
                <kc><?php echo encode();?></kc>
                <br />
                <?php getUseInfo($_G['uid']);?>
                <br />
                <pp>最新版本：1.0.1 可以直接在 PCL 启动器或者 HMCL 启动器搜索下载</pp>
                <pp>请勿加群后啥也不干直接退群，这么做会导致你的论坛账号被封禁，并且将禁止以后加群。</pp>
                <pp>每个编码仅限使用一次！</pp>
                <pp>因为经常有进群后啥也不干就退群，极大程度打扰群管，且 Q 群没有不接收信息的功能。希望各位理解~</pp>
</div>
</div>
</div>

<?php
    }

if(($_G['groupid'] <= 3 && $_G['groupid'] >= 1) || $_G['groupid'] == 23){
    ?>
    <div id="ct" class="wp cl"> 
        <div class="mn bm cl"> 
                <div class="bm_c">
                <h1>Q 群验证解码系统</center></h1>
                    <pp>在下方的表格中输入群验证信息获取到的编码，即可验证。</pp>
                    <br />
                    <pp>因为每个用户的识别编码仅限使用一次，一旦管理员或版主查询成功后该识别码立即失效。</pp>
                    <form method="post">
                    <input type="text">
                    <input type="submit">
                    <button onclick="document.getElementById('post').reset()">清空提交信息</button>
                    <?php
                    if(!empty($_POST["#"])){
                        $key = $_POST["#"];
                        $inv = 1;
                        $id = 0;
                        $time = 0;
                        $info = "";
                        try{
                            $id = intval(decodeID($key));
                            ##
                            $time = intval(decodeTime($key));
                            echo "<br/>";
                            $info = getUseInfo($id);
                            echo "<br/>";
                        }catch(Exception $e){
                        }
                        $ftime=addFirstTime($id);
                        if($id != 0){
                            setKeyUsed($id,$_G['username'],time());
                        }
                        ?>
                        <br />
                        <h2>查询的用户信息</h2>
                        <ti>用户ID：</ti><to><?php echo ($id == 0 ? "无效" : $id);?></to>
                        <br />
                        <ti>用户名：</ti><to><?php echo ($id == 0 ? "无效" : getName($id)); ?></to>
                        <br />
                        <ti>解码时间：</ti><to><?php ## ?></to>
                        <br />
                        <ti>记录时间：</ti><to><?php ## ?></to>
                        <br /><?php 
                        ?>
<br /><br /><p>提示：解码时间是根据编码获取的时间，记录时间是该用户实际访问该页面生成编码的时间，如果时间不一致请通知大轩。</p><hr /><br />
                        <h2>执行操作</h2>
                        <to><a href="https://biggestxuan.top/home.php?mod=space&uid=<?php echo $id;?>&do=profile" target="_blank">个人主页</a></to>
                        <br />
                        <to><a href="https://biggestxuan.top/forum.php?mod=modcp&action=member&op=ban&uid=<?php echo $id;?>" target="_blank">快速封禁</a></to>
                        <?php
                    }
                    ?>
                    </form>
</div>
</div>
</div>
<?php
}
include template('emcworld/download');
?>