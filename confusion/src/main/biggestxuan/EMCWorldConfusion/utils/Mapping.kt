package EMCWorldConfusion.utils

import EMCWorldConfusion.file.getAllFiles
import EMCWorldConfusion.file.getAllFunctions
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter


/*
*  EMC WORLD MOD
*  @Author Biggest_Xuan
*  2023/01/09
*/

val hasMap = ArrayList<String>()

fun getRandomMethodName():String{
    while (true){
        val name = getName("func_")
        if(!hasMap.contains(name)){
            hasMap.add(name)
            return name
        }
    }
}

fun getRandomArgName():String{
    while (true){
        val name = getName("arg_")
        if(!hasMap.contains(name)){
            hasMap.add(name)
            return name
        }
    }
}

fun getName(s:String):String{
    var name = s
    for(i in 0 .. 2){
        val randomInt = (Math.random() * 100).toInt()
        name += randomInt
    }
    name += "_"
    name += getRandomChar()
    name += getRandomChar()
    return name
}

fun getRandomChar():String{
    val index = 3
    val chars = "abcdefghiklmnopqrstuvwxyz"
    return chars[(Math.random() * 25).toInt()].toString()
}

fun genMapping(){
    val file = File("mapping/EMCWorldMapping.txt")
    if(!file.exists()){
        file.createNewFile()
    }
    val writer = FileWriter(file.path,true)
    val buffer = BufferedWriter(writer)
    for(s in getAllFiles()){
        buffer.write(s.path)
        buffer.newLine()
        for(k in getAllFunctions(s)){
            if(k.name != ""){
                buffer.write(k.toString())
                buffer.newLine()
                if(k.hasArg()){
                    for(a in k.args){
                        buffer.write(a.toString())
                        buffer.newLine()
                    }
                }
            }
        }
        buffer.newLine()
    }
    buffer.flush()
    buffer.close()
}