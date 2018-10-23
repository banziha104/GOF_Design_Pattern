package com.company.p_15_facade

import java.io.FileInputStream
import java.io.FileWriter
import java.io.IOException
import java.io.Writer
import java.util.*

class Database{
    companion object {
        fun getProperties(dbname : String) : Properties{
            val filename = dbname +".txt"
            val prop = Properties()
            try {
                prop.load(FileInputStream(filename))
            }catch (e : IOException){
                e.printStackTrace()
            }
            return prop
        }
    }
}

class HtmlWriter(val writer : Writer){
    fun title(title : String){
        writer.write("<html>")
        writer.write("<head>")
        writer.write("<title> $title </title>")
    }

    fun paragraph(msg : String) {
        writer.write("<p> $msg </p>")
    }

    fun link(href : String, username : String) = paragraph("<a href=/${href}>")
    fun close(){
        writer.write("</body>")
        writer.close()
    }
}


// 창구(Facade 역할을 수행)
class PageMaker{
    companion object {
        fun makeWelcomPage(mailaddr : String, filename : String){
            try{
                val mailprop = Database.getProperties("maildata")
                val username = mailprop.getProperty(mailaddr)
                val wt = HtmlWriter(FileWriter(filename))
                wt.title("abc")
                wt.paragraph("mailing")
            } catch (e : Exception){

            }
        }
    }
}

fun main(args: Array<String>) {
    PageMaker.makeWelcomPage("lee@gmail.com","welcome.html")
}