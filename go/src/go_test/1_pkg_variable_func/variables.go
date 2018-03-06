package main

import "fmt"

var c, python, java bool
var a, b int = 2, 3
var cc, pp, jj = 2, true, "no!"

func main() {
	fmt.Println("变量：")
	var i int
	fmt.Println(i)
	fmt.Println(a, b)
	fmt.Println(c, python, java)
	fmt.Println(cc, pp, jj)
	m := "我是 := 赋值的变量,我不能在函数外使用"
	fmt.Println(m)
}
