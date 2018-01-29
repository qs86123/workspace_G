package main

import "fmt"

func main() {
	fmt.Println("类型推导：")
	v := 0.867 + 0.5i
	fmt.Printf("v的类型是：%T\n值是：%v", v, v)
}
