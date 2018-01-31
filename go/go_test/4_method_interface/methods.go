package main

import (
	"fmt"
	"math/big"
)

//Go 没有类。然而，仍然可以在结构体类型上定义方法。
//方法接收者 出现在 func 关键字和方法名之间的参数中。
type Vertex struct {
	X, Y float64
}

//你可以对包中的 任意 类型定义任意方法，而不仅仅是针对结构体。
//但是，不能对来自其他包的类型或基础类型定义方法。
type MyFloat float64

func (f MyFloat) MyFloatAbs() float64 {
	if f < 0 {
		return float64(-f)
	}
	return float64(f)
}

func (v *Vertex) Abs() float64 {
	return v.X + v.Y
}

func (v Vertex) Abs2() float64 {
	return v.X + v.Y
}

func main() {
	v1 := Vertex{3, 4}
	v2 := &Vertex{3, 4}
	fmt.Printf("v1是Vertex类型:%T(%v)\nv2是指针类型:%T(%v)\n", v1, v1, v2, v2)
	fmt.Println(v1.Abs(), v2.Abs())
	fmt.Println(v1.Abs2(), v2.Abs2())
	fmt.Println("MyFloat-------------------------")
	f := MyFloat(-9)
	fmt.Println(f.MyFloatAbs())
}
