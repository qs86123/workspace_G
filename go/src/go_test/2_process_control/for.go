package main

import "fmt"

func main() {
	for1()
	for2()
	forRange()
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

func forRange() {
	pow := []int{1, 2, 3, 4, 5}
	for i, v := range pow {
		fmt.Printf("pow[%v] = %v\n", i, v)
	}
	fmt.Println("如果只需要索引，去掉v就可以（i，v只是变量名，可随意）")
	for i := range pow {
		fmt.Printf("i=%v\n", i)
	}
	fmt.Println("如果只需要v，将前面的参数用_代替")
	for _, v := range pow {
		fmt.Printf("v=%v\n", v)
	}

}
