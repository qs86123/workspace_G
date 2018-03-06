package main

import "fmt"

func main() {
	//defer会延迟函数的执行，直到上层函数返回,defer的上层函数就是使用了defer的那个函数，
	//通俗点讲：就是defer会在调用defer的函数返回之后再执行
	//这里main使用了defer，所以defer会在main返回之后执行
	//deferStack也使用了defer,deferStack里面的defer的上层函数就是deferStack,defer会在deferStack返回之后执行
	//所以结果可以看到：在程序输出“done”之后，开始执行deferStack里面的defer，在main函数输出i=9999之后，函数f()才执行
	defer f()
	fmt.Println("hello")
	//延迟的函数调用被压入一个栈中。当函数返回时， 会按照后进先出的顺序调用被延迟的函数调用。
	deferStack()
	for i := 0; i < 10000; i++ {
		if i == 9999 {
			fmt.Println("i=", i)
		}
	}
}

func f() {
	i := 0
	for ; i < 1; i++ {
	}
	fmt.Println("hello f")
}

func deferStack() {
	fmt.Println("counting...")
	for i := 0; i < 10; i++ {
		defer fmt.Println("defer-->", i)
	}
	fmt.Println("done")
}
