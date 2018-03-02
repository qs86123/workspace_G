package main

import "fmt"

const (
	a = iota
	b
	c
)
const (
	d = iota
	e
	f
)

func main() {

	fmt.Println(a, b, c, d, e, f)

	s := "hello"
	c := []byte(s)
	c[0] = 'p'
	s = string(c)
	fmt.Println(s)
	s = s + s[1:]
	fmt.Println(s)

	s = `sfas
safsad 
sadf sd f fds
sfd \n \twe`
	fmt.Println(s)

	var a [10]int
	fmt.Println(len(a), cap(a))

	rating := map[string]float32{"C": 5, "Go": 4.5, "Python": 4.5, "C++": 2}
	delete(rating, "sdf")

	var kk = []int8{1, 2, 3, 4}
	change(&kk)
	fmt.Println(kk)

	recovertest()

	f := funcs(testfun)
	f.alll(2, 3)
	human := Human{"rr", 4}
	human.string="tt"
	fmt.Println(human)
}

func change(a *[]int8) {
	*a = append(*a, int8(9))
}

var i = 0

func recovertest() {
	fmt.Println("is here")
	bb := false
	for i = 0; i < 5 && !bb; i++ {
		bb = reco()
		fmt.Println(bb)
	}
	fmt.Println("aaaaa")
}
func panictest() {
	if i != 3 {
		panic("sadf")
	}
}

func reco() (b bool) {
	defer func() {
		if x := recover(); x != nil {
			fmt.Println(i, "---aaab")
			b = false
		}
	}()
	panictest()
	b = true
	return
}

type Human struct {
	string
	int
}
type Student struct {
	Human
	sex string
}

type funcs func(a int) int

func (f funcs) alll(a, b int) {
	fmt.Println(f(a + b))
}

func testfun(a int) int {

	var aa interface{}
	var i int = 5
	s := "Hello world"
	// a 可以存储任意类型的数值
	aa = i
	value,_:=aa.(int)
	fmt.Println("sdaf",value)
	aa = s
	value2,_:=aa.(int)
	fmt.Println(value2)
	c:=make(chan int)
	v,ok:=<-c
	fmt.Println("sdfsafsdfsadf",v,ok)
	return a
}
