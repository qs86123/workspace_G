package main

import (
	"fmt"
	"math"
)

/**
从先前的练习中复制 Sqrt 函数，并修改使其返回 error 值。

由于不支持复数，当 Sqrt 接收到一个负数时，应当返回一个非 nil 的错误值。

创建一个新类型

type ErrNegativeSqrt float64
为其实现

func (e ErrNegativeSqrt) Error() string
使其成为一个 error， 该方法就可以让 ErrNegativeSqrt(-2).Error() 返回 `"cannot Sqrt negative number: -2"`。

*注意：* 在 Error 方法内调用 fmt.Sprint(e) 将会让程序陷入死循环。可以通过先转换 e 来避免这个问题：fmt.Sprint(float64(e))。请思考这是为什么呢？

修改 Sqrt 函数，使其接受一个负数时，返回 ErrNegativeSqrt 值。
 */

type ErrNegativeSqrt float64

func (e ErrNegativeSqrt) Error() string {
	return fmt.Sprintf("%v < 0 is wrong", float64(e))
}

func Sqrt(v float64) (float64, error) {
	if v < 0 {
		return 0, ErrNegativeSqrt(v)
	}
	return math.Sqrt(v), nil
}

func main() {
	v := float64(2)
	if z, err := Sqrt(v); err != nil {
		fmt.Println(err)
	} else {
		fmt.Printf("Sqrt(%v)=%v\n", v, z)
	}

	//在Error方法中调用fmt.Print(e)会陷入死循环，原因：
	//Error相当于是e的toString方法，调用Print时调用了e的Error，见下面的输出
	//如果在e的Error方法中再调用pring相当于是在e的Error方法中再调用了e的Error
	e := ErrNegativeSqrt(3)
	fmt.Println(e)
}
