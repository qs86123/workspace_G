package main

import (
	"fmt"
	"runtime"
	"time"
)

func main() {
	//case语句会自动终止，除非加了fallthrough，
	switch1()
	switch2()
	swith_ifelse()
}
func switch1() {
	fmt.Print("Go runs on ")
	switch s := runtime.GOOS; s {
	case "drawin":
		fmt.Println("OS X")
	case "linux":
		fmt.Println("linux")
	case "windows":
		fmt.Println("windows")
		//这里加了fallthrough，所以case语句在这里不会终止，而是继续执行下一个case或default，不管条件是否成立
		fallthrough
	case "other":
		fmt.Println("other")
		//由于这里没有加fallthrough，程序执行到这里的时候就自动终止了
	default:
		fmt.Println("default ", s)
	}
}

func switch2() {
	fmt.Println("哪天是星期六？")
	today := time.Now().Weekday()
	switch time.Saturday {
	case today + 0:
		fmt.Println("今天")
	case today + 1:
		fmt.Println("明天")
	case today + 2:
		fmt.Println("后天")
	default:
		fmt.Println("还早")
	}
}

func swith_ifelse() {
	t := time.Now()
	switch {
	case t.Hour() > 0 && t.Hour() < 8:
		fmt.Println("0<time<8")
	case t.Hour() < 10:
		fmt.Println("8<time<10")
	case t.Hour() < 12:
		fmt.Println("10<time<12")
	case t.Hour() < 16:
		fmt.Println("12<time<16")
	case t.Hour() < 24:
		fmt.Println("10<time<24")
	}
}
