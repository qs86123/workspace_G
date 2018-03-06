package main

import (
	"fmt"
	"math"
)

//函数也是值。他们可以像其他值一样传递，比如，函数值可以作为函数的参数或者返回值。
func main() {
	hypot := func(x, y float64) float64 {
		return x * y
	}
	fmt.Println(hypot(5, 12))
	fmt.Println(compute(hypot))
	fmt.Println(compute(math.Pow))

	fmt.Println("函数的闭包：------------------------------")
	a, b := adder(), adder()
	for i := 0; i < 10; i++ {
		fmt.Println(
			a(i),
			b(-2*i),
		)
	}
}

func compute(fn func(float64, float64) float64) float64 {
	return fn(3, 4)
}

func adder() func(int) int {
	sum := 0
	return func(x int) int {
		sum += x
		return sum
	}
}
