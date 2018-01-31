package main

import "fmt"

//map 在使用之前必须用 make 来创建；值为 nil 的 map 是空的，并且不能对其赋值。

type Vertex struct {
	Lat, Long float64
}

func main() {
	map1()
	map2()
	map3()
}

func map1() {
	fmt.Println("mp1------------------------------")
	var m map[string]int
	m = make(map[string]int)
	m["keyName"] = 3
	fmt.Println(m["keyName"])
}

func map2() {
	fmt.Println("mp2------------------------------")
	var m = map[string]Vertex{
		"Bell Labs": Vertex{1.1, 2.2},
		"Google":    Vertex{3.3, 4.4},
	}
	//若顶级类型只是一个类型名，则可以省略它
	mm := map[string]Vertex{
		"mm1": {5.5, 6.6},
		"mm2": {7.7, 8.8},
	}
	fmt.Println(m, mm)
}

func map3() {
	fmt.Println("mp3------------------------------")
	m := make(map[string]int)
	m["key"] = 21
	fmt.Println("m=", m["key"])
	m["key"] = 32
	fmt.Println("m=", m["key"])
	v1, ok := m["key"]
	fmt.Println("The value:", v1, "Present?", ok)
	delete(m, "key")
	v2, ok := m["key"]
	fmt.Println("The value:", v2, "Present?", ok)

}
