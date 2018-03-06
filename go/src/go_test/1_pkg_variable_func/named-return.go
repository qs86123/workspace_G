package main

import "fmt"

func main() {
	fmt.Println("函数返回值命名返回：")
	a, b := split(9)
	fmt.Println(a, b)
}

func split(sum int) (x, y int) {
	x = sum * 4 / 9
	y = sum - x
	return
}
