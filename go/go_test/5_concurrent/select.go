package main

import (
	"fmt"
)

/**
select 语句使得一个 goroutine 在多个通讯操作上等待。

select 会阻塞，直到某个条件分支中的<语句>可以继续执行，这时就会执行那个条件分支。当多个都准备好的时候，会随机选择一个。
示例中用了5个通道
结果：将fibonacci方法中输出的xx is run和sout函数输出分开（因为sout是在goroutine运行的，线程的输出时间可能不定，分开开好看些），
可以看到fibonacci方法中输出的xx is run的顺序和main函数中go func中sout函数输出的顺序是一样的，
咋一看好像和以上说的会随机喧杂一个有点相违背。
原因：
我们创建的通道是非缓冲的，我们只有在使用sout的时候，通道才是非阻塞的，所以，
也就是说，只有sout执行的时候select里面才会有case可以执行，其他时候所有case都是阻塞的
可以将通道改成缓冲通道试试，你会发现输出的结果完全不是按照顺序来的了
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
		}
	}

}
