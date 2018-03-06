package main

import "fmt"

//通道：默认情况下，在另一端准备好之前，发送和接收都会阻塞。这使得 goroutine 可以在没有明确的锁或竞态变量的情况下进行同步

//channel 可以是 带缓冲的。为 make 提供第二个参数作为缓冲长度来初始化一个缓冲 channel：
//ch := make(chan int, 100)
//向带缓冲的 channel 发送数据的时候，只有在缓冲区满的时候才会阻塞。 而当缓冲区为空的时候接收操作会阻塞。

//注意：带缓冲的通道可以不使用goroutine也可以，看方法chennel2(),但是如果缓冲区不够，则会报错，
//注意：缓冲区的长度指的是能够放入该通道的次数，并不是指大小，可以看第chennel2()，缓冲区大小只有2，但是数据很大都是超过2字节的
func main() {
	channel1()
	channel2()
}

func channel1() {
	fmt.Println("默认通道--------------------")
	a := []int{1, 2, 3, 4, 5, 6}
	c := make(chan int)
	go sum(a[:len(a)/2], c)
	go sum(a[len(a)/2:], c)
	x, y := <-c, <-c
	fmt.Println(x, y)
}

func channel2() {
	fmt.Println("带缓冲通道--------------------")
	c := make(chan string, 2)
	c <- "hello"
	c <- "word"
	x, y := <-c, <-c
	fmt.Println(x, y)
}

func sum(a []int, c chan int) {
	sum := 0
	for _, v := range a {
		sum += v
	}
	c <- sum
}
