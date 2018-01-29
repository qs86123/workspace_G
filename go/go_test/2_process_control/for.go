package main

import "fmt"

func main() {
	for1()
	for2()
}

func for1() {
	sum := 0
	for i := 0; i < 10; i++ {
		sum += i
	}
	fmt.Println(sum)
}

func for2() {
	sum := 0
	i := 1
	for sum < 50 {
		sum += i
	}
	fmt.Println(sum)
}
