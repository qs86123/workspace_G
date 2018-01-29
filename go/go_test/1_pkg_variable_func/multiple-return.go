package main

import "fmt"

func main() {
	fmt.Println("函数多值返回：")
	a, b := swap(2, 3)
	fmt.Println(a, b)
}

func swap(x, y int) (int, int) {
	return y, x
}
