package main

import (
	"fmt"
	"time"
)

/**
select的default语句，永远属于准备好的，代码相比select.go仅仅多了default这部分

因为default是永远不会阻塞的，因此我们可以把quit<-0放到default当中去，当达到某个条件时放入一个值到quit当中，
但是注意：如果要在default当中向通道写入值，quit通道一定要写成带缓冲的，因为不带缓冲的通道，要写值必须通过goroutine的方式进行
或者写成go func()的形式（这种形式是在线程中执行，线程可能会延时，所有不建议采用这种方式）
 */
func main() {
	c := make(chan int)
	c2 := make(chan int)
	c3 := make(chan int)
	c4 := make(chan int)
	c5 := make(chan int)
	quit := make(chan int)
	go func() {
		for i := 0; i < 2; i++ {
			sout("c", c)
			sout("c3", c3)
			sout("c2", c2)
			sout("c5", c5)
			sout("c4", c4)
		}
		quit <- 0
		fmt.Println("case <-quit can run this time")
	}()
	fibonacci(c, c2, c3, c4, c5, quit)
}

func sout(s string, c chan int) {
	if vc, ok := <-c; ok {
		fmt.Printf("from %v, value = %v\n", s, vc)
	}
}

func fibonacci(c, c2, c3, c4, c5, quit chan int) {
	x, y := 1, 1
	for {
		select {
		case c <- x:
			fmt.Println("c is run")
			x, y = y, x+y
		case c2 <- x:
			fmt.Println("c2 is run")
			x, y = y, x+y
		case c3 <- x:
			fmt.Println("c3 is run")
			x, y = y, x+y
		case c4 <- x:
			fmt.Println("c4 is run")
			x, y = y, x+y
		case c5 <- x:
			fmt.Println("c5 is run")
			x, y = y, x+y
		case <-quit:
			fmt.Println("quit")
			return
		default:
			fmt.Println("this is default,sleep 3 seconds")
			time.Sleep(3 * time.Second)
		}
	}

}
