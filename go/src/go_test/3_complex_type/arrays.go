package main

import (
	"fmt"
	"strings"
)

//参考：https://blog.go-zh.org/go-slices-usage-and-internals
func main() {
	array1()
	arraySlice1()
	arraySlice2()
	sliceAppend()
}
func array1() {
	var a [2]string
	a[0] = "hello"
	a[1] = "word"
	fmt.Println(a[0], a[1])
	fmt.Println(a)
}
func arraySlice1() {
	s := []int{0, 1, 2, 3, 4, 5}
	fmt.Println("s==", s)
	//切割数组成为新的数组，含头不含尾
	s1 := s[1:4]
	//slice创建的切片是指向原数组的指针，修改值会影响原数组的值
	s1[2] = 99999
	fmt.Println("s[1:4] = ", s1)
	fmt.Println("省略下标，表示从头开始截取：s[:2] = ", s[:2])
	fmt.Println("省略上标，表示从某处开始截取到最后：s[2:] = ", s[2:])
	fmt.Println("直接输出截取后的某个值：s[2:][3] = ", s[2:][3])

	b := [][]string{
		{"1", "2", "3", "4"},
		{"5", "6", "7", "8"},
		{"9", "10", "11", "12"},
		{"13", "14", "15", "16"},
	}
	bb := b[1:3]
	for i := 0; i < len(bb); i++ {
		fmt.Printf("%s\n", strings.Join(bb[i], "--"))
	}
}

func arraySlice2() {
	//第二个参数指定数组当前长度，没有指定容量时，数组容量就为长度
	a := make([]int, 0)
	printSlice("a", a)
	//不是通过make创建的数组零值为nil
	var aa []int
	printSlice("aa", aa)
	if a == nil {
		fmt.Println("a is nil")
	} else {
		fmt.Println("a is not nil")
	}
	if aa == nil {
		fmt.Println("aa is nil")
	} else {
		fmt.Println("aa is not nil")
	}
	//第二个参数指定数组当前长度，len(b),第三个参数指定数组容量cap(b)，
	b := make([]int, 1, 5)
	printSlice("b", b)
	c := b[:2]
	printSlice("c", c)
	d := b[2:5]
	printSlice("d", d)
}

func sliceAppend() {
	fmt.Println("sliceAppend--------------------")
	var a []int
	printSlice("a", a)
	a = append(a, 1)
	printSlice("a", a)
	a = append(a, 2)
	printSlice("a", a)
}

func printSlice(s string, a []int) {
	fmt.Printf("%s len=%d cap=%d %v\n", s, len(a), cap(a), a)
}
