package main

import (
	"fmt"
	"math"
)

func main() {
	if1(-1)
	if1(1)
	fmt.Println(pow(2, 3, 10))
	fmt.Println(pow(2, 4, 10))
}

func if1(x int) {
	if x < 0 {
		fmt.Println("x < 0")
		return
	}
	fmt.Println("x >= 0")
}

func pow(x, y, limit float64) float64 {
	//if 语句可以在条件之前执行一个简单语句。
	//当然，如果用不到变量v的话也可以直接写if math.Pow(x,y)<limit{}
	//定义的便捷变量v同样可以在else语句中使用
	if v := math.Pow(x, y); v < limit {
		return v
	} else {
		fmt.Printf("	%g >= %g\n", v, limit)
	}
	return limit
}
