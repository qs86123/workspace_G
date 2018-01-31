package main

import (
	"fmt"
	"sync"
	"time"
)

// SafeCounter 的并发使用是安全的，
// 由于map不能并发读写，所以加锁是必要的，而且是安全的
type SafeCounter struct {
	v   map[string]int
	mux sync.Mutex
}

func (sc *SafeCounter) Inc(key string) {
	sc.mux.Lock()
	sc.v[key]++
	sc.mux.Unlock()
}
func (sc *SafeCounter) Value(key string) int {
	sc.mux.Lock()
	defer sc.mux.Unlock()
	return sc.v[key]
}
func main() {
	sc := SafeCounter{v: make(map[string]int)}
	for i := 0; i < 1000; i++ {
		go sc.Inc("someKey")
	}
	time.Sleep(time.Second)
	fmt.Println(sc.Value("someKey"))
}
