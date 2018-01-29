package main

import "fmt"

func main() {
	//定义一个指向类型T的指针
	var p *int
	fmt.Println(p, &p)
	i, j := 8, 9
	//&：生成一个指向其作用对象的指针，通俗点将就是，取i的地址
	p = &i
	//定义一个指针，执行类型自动判断
	p2 := &j
	fmt.Println(p, &p, *p)
	fmt.Println(p2, &p2, *p2)
	//*：表示指针指向的底层的值，通俗点讲就是，取（改）该地址的值
	*p = 88
	*p2 = 99
	fmt.Println(p, &p, *p)
	fmt.Println(p2, &p2, *p2)
}
