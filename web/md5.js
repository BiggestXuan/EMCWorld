/**
 * Author:Biggest_Xuan
 * 2023/6/27
 */

 function a(){
    const f = document.getElementById("file").files[0];
    var fileReader = new FileReader()
    var spark = new SparkMD5()
    fileReader.readAsBinaryString(f)
    fileReader.onload = (e) => {
        spark.appendBinary(e.target.result)
        const md5 = spark.end()
        setMD5(md5);
        check();
    }
}

function setMD5(s){
    document.getElementById("md5").innerHTML = s;
}

function check(){
    const md5 = document.getElementById("md5").innerHTML;
    var i = 1;
    while(document.getElementById("s"+i) != null){
        const version = document.getElementById("v"+i).innerHTML;
        if(md5 === version){
            document.getElementById("s"+i).innerHTML = "<i class='fico-valid fc-v'></i>";
        }else{
            document.getElementById("s"+i).innerHTML = "<i class='fico-invalid fc-i'></i>";
        }
        i += 1;
    }
}