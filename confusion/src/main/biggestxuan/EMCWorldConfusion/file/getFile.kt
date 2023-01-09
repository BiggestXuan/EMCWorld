package EMCWorldConfusion.file

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collector
import java.util.stream.Collectors
import java.util.stream.Stream


/*
*  EMC WORLD MOD
*  @Author Biggest_Xuan
*  2023/01/09
*/

fun getAllFilesPath() : List<Path>? {
    val path = "scripts/"
    try {
        val paths = Files.walk(Paths.get(path));
        val fileNames = paths.filter(Files::isRegularFile).collect(Collectors.toList())
        return fileNames
    }catch (e : IOException){
        e.printStackTrace()
    }
    return null
}

fun getAllFiles() : List<File>{
    val paths = getAllFilesPath()
    val files = ArrayList<File>()
    if (paths != null) {
        for(p in paths){
            files.add(p.toFile())
        }
    }
    return files
}


