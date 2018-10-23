# Facade

> 단순한 창구 

- 프로그램이라는 것은 점점 커지는 경향이 있음
- 커다란 프로그램을 사용해서 처리하려면 상호 관련된 많은 클래스를 적절하게 제어해야함 
- 이에 처리를 실행하기 위한 창구를 준비해 두는 것이 좋음 

---

<br>

- ### 필요 클래스 

| 이름        | 해설                                                           | 대응 클래스          |
|-------------|----------------------------------------------------------------|----------------------|
| Facade      | 많은 역할에 대해 `단순한 창구`가 됨                            | PageMaker            |
| 구성 클래스 | 각각의 임무를 실행하지만, Facade 역할에 대해서는 신경쓰지 않음 | Database, HtmlWriter |
| Client      | Facade를 통해 실행을 주문                                      | Main                 |

<br>

- ### 예제 클래스

| 이름       | 해설                                              |
|------------|---------------------------------------------------|
| Database   | 메일 주소에서 사용자 이름을 얻는 클래스           |
| HtmlWriter | HTML 파일을 작성하는 클래스                       |
| PageMaker  | 메일 주소에서 사용자의 웹페이지를 작성하는 클래스 |
| Main       | 동작 테스트용 클래스                              |

<br>

- ### 전체소스

```kotlin
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
```