package main

import "fmt"

//定义一个接口
type Abser interface {
	MyAbs() float64
}

func main() {
	var a Abser
	f := MyFloat(-9)
	v := Vertex{1, 2}
	a = f
	fmt.Println(a.MyAbs())
	//a是接口类型，Vertex并没有实现接口，实现接口的是*Vertex，所以下面这句代码是错的
	//a = v
	a = &v
	fmt.Println(a.MyAbs())
}

type MyFloat float64

type Vertex struct {
	X, Y float64
}

func (f MyFloat) MyAbs() float64 {
	if f < 0 {
		return float64(-f)
	}
	return float64(f)
}

func (v *Vertex) MyAbs() float64 {
	return v.X + v.Y
}
