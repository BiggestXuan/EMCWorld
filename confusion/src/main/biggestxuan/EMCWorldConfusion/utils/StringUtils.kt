package EMCWorldConfusion.utils


/*
*  EMC WORLD MOD
*  @Author Biggest_Xuan
*  2023/01/09
*/

fun getStringCount(s:String,c:String):Int{
    var count = 0
    var index = 0
    if(!s.contains(c)) return count
    while (true){
        if(s.indexOf(c,index) != -1){
            count++
            index = s.indexOf(c,index)+1
        }else break
    }
    return count
}

fun removeHeadBlank(s : String):String{
    return if(s[0].toString() == " ") s.substring(1,s.length) else s
}