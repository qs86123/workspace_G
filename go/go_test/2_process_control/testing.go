package main

import "fmt"

func main() {
	//小练习，牛顿法开根号
	Sqrt(16)
}

func Sqrt(x float64) float64 {
	z := x
	newz := float64(1)
	for count := 0; z != newz && count < 100; count++ {
		z = newz
		newz = z - (z*z-x)/2/z
		fmt.Printf("count=%v   newz=%v\n", count, newz)
	}
	return newz
}
