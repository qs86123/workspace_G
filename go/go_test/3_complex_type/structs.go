package main

import "fmt"

type Vertex struct {
	//结构体中的同类型参数，也可以省略前面参数的类型，只保留最后一个
	X int
	Y int
}

var (
	//结构体文法
	//类型为Vertex
	v1 = Vertex{1, 2}
	//Y:0被省略
	v2 = Vertex{X: 1}
	//X:0,Y:0被省略
	v3 = Vertex{}
	//类型为*Vertex,注意,由于:=只能在方法体中使用，所以这里赋值直接用=,
	pp = &Vertex{1, 2}
)

func main() {
	fmt.Println(Vertex{1, 2})
	vertex1()
	vertexPointers()
	vertexLiterals()
}

func vertex1() {
	v := Vertex{3, 4}
	fmt.Println(v.X)
}

func vertexPointers() {
	v := Vertex{5, 6}
	p := &v
	p.X = 1e9
	fmt.Println(v)
}

func vertexLiterals() {
	fmt.Println(v1, v2, v3, pp)
	fmt.Printf("{%v %v}", pp.X, pp.Y)
}
