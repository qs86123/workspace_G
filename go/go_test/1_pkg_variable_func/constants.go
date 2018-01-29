package main

import "fmt"

const Pi = 3.14
const (
	Big = 1 << 4
	//移位操作操作的话，高位补零，所以这里Small的值是0，并不是0.5
	Small float64 = float64(Big >> 5)
)

func main() {
	fmt.Println("常量：")
	f := "%T(%v)\n"
	fmt.Printf(f, Pi, Pi)
	fmt.Printf(f, Big, Big)
	fmt.Printf(f, Small, Small)
}
