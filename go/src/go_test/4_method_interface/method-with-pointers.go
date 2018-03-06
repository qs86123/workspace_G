package main

import "fmt"

//不管是指针调用，还是引用调用，如果方法的接收者不是一个指针，都不会影响原始数据
type Vertex struct {
	X, Y float64
}

//方法接收者是指针，调用时使用原始值，操作会改变原始数据
func (v *Vertex) Scale(f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

//方法接受者不是一个指针，调用时不会使用原始值，使用的是副本
func (v Vertex) Scale2(f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

func main() {
	v := Vertex{3, 4}
	v.Scale(2)
	fmt.Println("调用指针方法放大两倍", v)
	v.Scale2(2)
	fmt.Println("调用<非>指针方法<再>放大两倍", v)
	p := &v
	p.Scale(2)
	fmt.Printf("p是v的指针，p调用指针方法<再>放大两倍,p=%+v  *p=%v  v=%v\n", p, *p, v)
	p.Scale2(2)
	fmt.Printf("p是v的指针，p调用<非>指针方法<再>放大两倍,p=%+v  *p=%v  v=%v\n", p, *p, v)
}
