package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println("类型转换：")
	var x, y int = 3, 4
	var f float64 = math.Sqrt(float64(x*x + y*y))
	//类型转换
	var z uint = uint(f)
	//或者
	var kk float64 = 9.8
	k := uint(kk)
	fmt.Println(x, y, z, k)
}
