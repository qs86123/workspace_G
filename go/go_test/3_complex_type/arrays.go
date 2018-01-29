package main

import (
	"fmt"
	"strings"
)

func main() {
	array1()
	arraySlice1()
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
	fmt.Println("s[1:4] = ", s1)
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
