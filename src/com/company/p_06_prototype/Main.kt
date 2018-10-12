package com.company.p_06_prototype

fun main(args: Array<String>) {
    val manager = Manager()
    val upen = UnderlinePen('_')
    val mbox = MessageBox('*')

    // 메니저에 등록
    manager.register("upen",upen)
    manager.register("mbox",mbox)

    // 생성
    val p1 : Product? = manager.create("upen")
    val p2 : Product? = manager.create("mbox")

    p1?.use("abc")
    p2?.use("cde")

}