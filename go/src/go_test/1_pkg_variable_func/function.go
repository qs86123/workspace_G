package main

import "fmt"

func main() {
	fmt.Println("函数：")
	fmt.Println("标准写法：", add(1, 2))
	fmt.Println("同类型参数省略前面参数的类型：", add2(1, 2))
}

func add(x int, y int) int {
	return x + y
}

func add2(x, y int) int {
	return x + y
}
